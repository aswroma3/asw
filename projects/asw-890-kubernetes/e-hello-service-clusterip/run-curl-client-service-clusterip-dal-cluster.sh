#!/bin/bash

# accede al servizio tramite service clusterip - sull'ip associato al service, sulla porta 8080 
# FUNZIONA SOLO DAI NODI DEL CLUSTER ma non da DEV 

SERVICE=hello-svc

SERVICE_HOST=$(kubectl get services/${SERVICE} | grep ${SERVICE} | awk '{print $3}') 
SERVICE_PORT=8080

echo Accessing ${SERVICE} on ${SERVICE_HOST}:${SERVICE_PORT}

N=${1:-10}
for ((i=0; i<$N; i++)); do 
	curl ${SERVICE_HOST}:${SERVICE_PORT}
	echo "" ; 
done 
