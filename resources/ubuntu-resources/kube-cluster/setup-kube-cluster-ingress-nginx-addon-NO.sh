#!/bin/bash

# aggiornato a dicembre 2023  
# NON PIU' MANTENUTO 

# nota: ci sono ingress controller basati su nginx: 1) uno mantenuto da nginx e 2) uno da kubernetes
# 1) https://www.nginx.com/products/nginx-ingress-controller 
#    https://github.com/nginxinc/kubernetes-ingress  
# 2) https://github.com/kubernetes/ingress-nginx/blob/main/README.md 
#    https://kubernetes.github.io/ingress-nginx/deploy/ 

# questo script installa il secondo (malgrado non sia possibile accedervi sulla porta 80) 
# ascolta http/https su 31080/31443

echo "======================================================="
echo "installing kubernetes nginx ingress controller (master)"
echo "======================================================="

INGRESS_NGINX_FOLDER=/home/vagrant/kube-cluster/ingress-nginx

mkdir -p ${INGRESS_NGINX_FOLDER}

# kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.5.1/deploy/static/provider/cloud/deploy.yaml

# modifica il file di configurazione in modo che la porta per http/https dell'ingress siano fissate su 31080/31443 

curl -s -L https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.8.2/deploy/static/provider/baremetal/deploy.yaml \
        | sed 's/targetPort: http$/&\n    nodePort: 31080/' \
        | sed 's/targetPort: https$/&\n    nodePort: 31443/' \
		> ${INGRESS_NGINX_FOLDER}/ingress-nginx-nodeport-deploy.yaml

kubectl apply -f ${INGRESS_NGINX_FOLDER}/ingress-nginx-nodeport-deploy.yaml

# test: 
# kubectl get pods --namespace=ingress-nginx (il controller deve essere Running)
# kubectl get service ingress-nginx-controller --namespace=ingress-nginx (la porta 80 deve essere mappata su 31080)

# uninstall: 
# kubectl delete namespace ingress-nginx
