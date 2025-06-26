#!/bin/bash

# accede al servizio sull'ip associato al pod, sulla porta 8080 
# FUNZIONA SOLO DAI NODI DEL CLUSTER, ma non da DEV 

APP=hello
# POD=hello-pod
POD=$(kubectl get pods -l app=${APP} | grep ${APP} | awk '{print $1}')
POD_HOST=$(kubectl describe pod ${POD} | grep ^IP: | awk '{print $2}')
POD_PORT=8080

echo Accessing ${POD} on ${POD_HOST}:${POD_PORT}

N=${1:-10}
for ((i=0; i<$N; i++)); do 
	curl ${POD_HOST}:${POD_PORT}
	echo "" ; 
done 
