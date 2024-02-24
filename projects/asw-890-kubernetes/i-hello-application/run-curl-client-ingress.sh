#!/bin/bash

# accede al servizio tramite ingress - ovvero, tramite il nome del servizio 
# funziona anche dall'esterno del cluster (dal nodo dev)  

# NEL CLUSTER DEVE ESSERE INSTALLATO L'INGRESS CONTROLLER NGINX INGRESS COME ADD-ON, 
# CHE ASCOLTA SULLA PORTA 31080 (tutti i nodi) E 80 (solo nodi worker)
# il nome del servizio dovrebbe essere registrato come alias in un DNS o in /etc/hosts, ma qui usiamo un trucco per simularlo  

SERVICE=hello
SERVICE_INGRESS_HOST=hello.aswroma3.it

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

echo Accessing ${SERVICE} on ${SERVICE_INGRESS_HOST}:${INGRESS_PORT}

N=${1:-10}
for ((i=0; i<$N; i++)); do 
	curl ${SERVICE_INGRESS_HOST} --connect-to ${SERVICE_INGRESS_HOST}:80:${SERVICE_HOST}:${INGRESS_PORT} 
	echo "" ; 
	# oppure 
#	curl ${SERVICE_HOST} --header "Host: ${SERVICE_INGRESS_HOST}" 
#	echo "" ; 
done 

