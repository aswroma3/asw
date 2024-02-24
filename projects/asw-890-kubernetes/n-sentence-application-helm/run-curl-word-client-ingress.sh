#!/bin/bash

# accede al servizio per una parola tramite ingress  

WORD=${1:-subject}

N=${2:-10}

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
# SERVICE_HOST=kube-node
# oppure
# SERVICE_HOST=kube-cluster

echo Accessing ${SERVICE}/${WORD} on ${SERVICE_INGRESS_HOST}:${INGRESS_PORT}/${WORD}

for ((i=0; i<$N; i++)); do 
	curl ${SERVICE_INGRESS_HOST}/${WORD} --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT} 
	echo "" ; 
	# oppure 
#	curl ${SERVICE_HOST}/${WORD} --header "Host: ${SERVICE_INGRESS_HOST}" 
#	echo "" ; 
done 

