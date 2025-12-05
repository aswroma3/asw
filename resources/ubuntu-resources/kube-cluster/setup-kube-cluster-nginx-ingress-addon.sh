#!/bin/bash

source "/home/asw/resources/common.sh"

# nota: ci sono ingress controller basati su nginx: 1) uno mantenuto da nginx e 2) uno da kubernetes
# 1) https://www.nginx.com/products/nginx-ingress-controller 
#    https://github.com/nginxinc/kubernetes-ingress  
# 2) https://github.com/kubernetes/ingress-nginx/blob/main/README.md 
#    https://kubernetes.github.io/ingress-nginx/deploy/ 
# questo script installa il primo 

# in questa configurazione, l'ingress controller ascolta http su 
# - la porta 31080 di tutti i nodi del cluster 
# - la porta 80 di tutti i nodi WORKER del cluster 

echo "======================================================="
echo "installing kubernetes nginx ingress controller (master)"
echo "======================================================="

NGINX_INGRESS_FOLDER=/home/vagrant/kube-cluster/nginx-ingress
mkdir -p ${NGINX_INGRESS_FOLDER}

# https://docs.nginx.com/nginx-ingress-controller/installation/installation-with-manifests/ 
# https://docs.nginx.com/nginx-ingress-controller/releases/

#NGINX_INGRESS_VERSION=v5.1.1
NGINX_INGRESS_VERSION=v5.2.1

# attenzione: usare una versione specifica e non usare il branch "main"
NGINX_GITHUB_URL=https://raw.githubusercontent.com/nginxinc/kubernetes-ingress/${NGINX_INGRESS_VERSION}

kubectl apply -f ${NGINX_GITHUB_URL}/deployments/common/ns-and-sa.yaml
kubectl apply -f ${NGINX_GITHUB_URL}/deployments/rbac/rbac.yaml

#kubectl apply -f https://raw.githubusercontent.com/nginxinc/kubernetes-ingress/v3.3.2/examples/shared-examples/default-server-secret/default-server-secret.yaml
kubectl apply -f ${ASW_RESOURCES}/kube-cluster/nginx-ingress/default-server-secret.yaml
kubectl apply -f ${NGINX_GITHUB_URL}/deployments/common/nginx-config.yaml

kubectl apply -f ${NGINX_GITHUB_URL}/deployments/common/ingress-class.yaml

kubectl apply -f ${NGINX_GITHUB_URL}/config/crd/bases/k8s.nginx.org_virtualservers.yaml
kubectl apply -f ${NGINX_GITHUB_URL}/config/crd/bases/k8s.nginx.org_virtualserverroutes.yaml
kubectl apply -f ${NGINX_GITHUB_URL}/config/crd/bases/k8s.nginx.org_transportservers.yaml
kubectl apply -f ${NGINX_GITHUB_URL}/config/crd/bases/k8s.nginx.org_policies.yaml
kubectl apply -f ${NGINX_GITHUB_URL}/config/crd/bases/k8s.nginx.org_globalconfigurations.yaml

# deploy the ingress as a single controller pod
#kubectl apply -f ${NGINX_GITHUB_URL}/deployments/deployment/nginx-ingress.yaml
# deploy the ingress with a controller pod on each node 
kubectl apply -f ${NGINX_GITHUB_URL}/deployments/daemon-set/nginx-ingress.yaml

# per default l'ingress ascolta su tutti i nodi su una porta random 
#kubectl create -f ${NGINX_GITHUB_URL}/deployments/service/nodeport.yaml

# configura invece l'ingress per ascoltare su tutti i nodi sulle porte 31080 e 31443 
curl -s -L ${NGINX_GITHUB_URL}/deployments/service/nodeport.yaml \
        | sed 's/targetPort: 80$/&\n    nodePort: 31080/' \
        | sed 's/targetPort: 443$/&\n    nodePort: 31443/' \
		> ${NGINX_INGRESS_FOLDER}/nodeport.yaml
kubectl apply -f ${NGINX_INGRESS_FOLDER}/nodeport.yaml

# test: 
# kubectl get pods --namespace=nginx-ingress (il controller deve essere Running)
# kubectl get service nginx-ingress --namespace=nginx-ingress (il controller deve essere di tipo NodePort, sulle porte 80/31080 e 443/31443)

# uninstall: 
# kubectl delete namespace nginx-ingress
