#!/bin/bash

# accede al servizio per una parola tramite nodeport  

WORD=${1:-subject}

N=${2:-10}

SERVICE=apigateway
SERVICE_NAMESPACE=sentence

SERVICE_HOST=kube-node
# oppure 
#SERVICE_HOST=kube-cluster

# se specificato nel file di deployment dell'applicazione 
# SERVICE_NODEPORT=32081
# se assegnato in modo random, lo trovo così 
SERVICE_NODEPORT=$(kubectl get services/${SERVICE} -n ${SERVICE_NAMESPACE} -o go-template='{{(index .spec.ports 0).nodePort}}')

echo Accessing ${SERVICE}/${WORD} on ${SERVICE_HOST}:${SERVICE_NODEPORT}/${WORD}

N=${1:-10}
for ((i=0; i<$N; i++)); do  
	curl ${SERVICE_HOST}:${SERVICE_NODEPORT}/${WORD}
	echo "" ; 
done 


