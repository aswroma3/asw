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
sudo mkdir -p ${NGINX_INGRESS_FOLDER}

# https://docs.nginx.com/nginx-ingress-controller/installation/installation-with-manifests/ 

# attenzione: usare una versione specifica e non usare il branch "main"
GITHUB_URL=https://raw.githubusercontent.com/nginxinc/kubernetes-ingress/v3.3.2/deployments/

kubectl apply -f ${GITHUB_URL}common/ns-and-sa.yaml
kubectl apply -f ${GITHUB_URL}rbac/rbac.yaml

#kubectl apply -f https://raw.githubusercontent.com/nginxinc/kubernetes-ingress/v3.3.2/examples/shared-examples/default-server-secret/default-server-secret.yaml
kubectl apply -f ${ASW_RESOURCES}/kube-cluster/nginx-ingress/default-server-secret.yaml
kubectl apply -f ${GITHUB_URL}common/nginx-config.yaml

kubectl apply -f ${GITHUB_URL}common/ingress-class.yaml

kubectl apply -f ${GITHUB_URL}common/crds/k8s.nginx.org_virtualservers.yaml
kubectl apply -f ${GITHUB_URL}common/crds/k8s.nginx.org_virtualserverroutes.yaml
kubectl apply -f ${GITHUB_URL}common/crds/k8s.nginx.org_transportservers.yaml
kubectl apply -f ${GITHUB_URL}common/crds/k8s.nginx.org_policies.yaml

kubectl apply -f ${GITHUB_URL}common/crds/k8s.nginx.org_globalconfigurations.yaml

kubectl apply -f ${GITHUB_URL}deployment/nginx-ingress.yaml
kubectl apply -f ${GITHUB_URL}daemon-set/nginx-ingress.yaml

# l'ingress ascolta su tutti i nodi su una porta random 
#kubectl create -f ${GITHUB_URL}service/nodeport.yaml

# configura invece l'ingress per ascoltare su tutti i nodi sulle porte 31080 e 31443 
curl -s -L ${GITHUB_URL}service/nodeport.yaml \
        | sed 's/targetPort: 80$/&\n    nodePort: 31080/' \
        | sed 's/targetPort: 443$/&\n    nodePort: 31443/' \
		> ${NGINX_INGRESS_FOLDER}/nodeport.yaml
kubectl apply -f ${NGINX_INGRESS_FOLDER}/nodeport.yaml

# test: 
# kubectl get pods --namespace=nginx-ingress (il controller deve essere Running)
# kubectl get service nginx-ingress --namespace=nginx-ingress (il controller deve essere di tipo NodePort, sulle porte 80/31080 e 443/31443)

# uninstall: 
# kubectl delete namespace nginx-ingress
