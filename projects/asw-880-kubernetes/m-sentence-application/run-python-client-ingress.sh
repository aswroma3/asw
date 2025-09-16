#!/bin/bash

# numero di chiamate 
N=${1:-10}

# intervallo tra una chiamata e l'altra (ms) 
DELAY=${2:-500}

# accede al servizio tramite service ingress - sulla porta del servizio, su uno dei nodi del cluster  

SERVICE=sentence
SERVICE_INGRESS_HOST=sentence.aswroma3.it

# nei nodi worker, l'ingress controller NGINX INGRESS ascolta sulle porte 80 (http) e 443 (https)  
INGRESS_PORT=80
# oppure 
# INGRESS_PORT=$(kubectl get services/nginx-ingress -n nginx-ingress -o go-template='{{(index .spec.ports 0).targetPort}}')
SERVICE_HOST=kube-node

# in alternativa, su tutti i nodi l'ingress controller NGINX INGRESS ascolta sulle porte 31080 (http) e 31443 (https)  
# INGRESS_PORT=31080
# oppure 
# INGRESS_PORT=$(kubectl get services/nginx-ingress -n nginx-ingress -o go-template='{{(index .spec.ports 0).nodePort}}')
# SERVICE_HOST=kube-worker
# oppure
# SERVICE_HOST=kube-cluster

echo Accessing ${SERVICE_INGRESS_HOST} on ${SERVICE_HOST}:${INGRESS_PORT}

source /home/asw/venv/bin/activate 

python3 -m rest-python-client-ingress $N $DELAY ${SERVICE_INGRESS_HOST} ${SERVICE_HOST} ${INGRESS_PORT}

