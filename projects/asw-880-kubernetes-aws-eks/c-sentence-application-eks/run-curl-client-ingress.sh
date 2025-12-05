#!/bin/bash

# accede al servizio tramite ingress - ovvero, tramite il nome del servizio 
# funziona anche dall'esterno del cluster (dal nodo dev)  

# NEL CLUSTER DEVE ESSERE INSTALLATO L'INGRESS CONTROLLER NGINX INGRESS COME ADD-ON, 
# CHE ASCOLTA SULLA PORTA 31080 (tutti i nodi) E 80 (solo nodi worker)
# il nome del servizio dovrebbe essere registrato come alias in un DNS o in /etc/hosts, ma qui usiamo un trucco per simularlo  

SERVICE=sentence
SERVICE_INGRESS_HOST=sentence.aswroma3.it

#AWS_HOST=$(kubectl get ingress -n $SERVICE | tail -1 | awk '{ print $4 }')
AWS_HOST=$(kubectl get ingress -n $SERVICE -o go-template='{{(index (index .items 0).status.loadBalancer.ingress 0).hostname}}')

echo Accessing ${SERVICE} on ${SERVICE_INGRESS_HOST} on ${AWS_HOST}

N=${1:-10}
for ((i=0; i<$N; i++)); do 
	curl ${AWS_HOST} --header "Host: ${SERVICE_INGRESS_HOST}" 
	echo "" ; 
done 


