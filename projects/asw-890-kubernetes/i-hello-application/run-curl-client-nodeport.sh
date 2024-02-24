#!/bin/bash

# accede al servizio tramite service nodeport - sulla porta del servizio, su uno dei nodi del cluster  
# funziona anche dall'esterno del cluster (dal nodo dev)  

SERVICE=hello-svc
SERVICE_NAMESPACE=hello

SERVICE_HOST=kube-node
# oppure 
#SERVICE_HOST=kube-cluster

# se specificato nel file di deployment dell'applicazione 
# SERVICE_NODEPORT=32081
# se assegnato in modo random, lo trovo così 
SERVICE_NODEPORT=$(kubectl get services/${SERVICE} -n ${SERVICE_NAMESPACE} -o go-template='{{(index .spec.ports 0).nodePort}}')

echo Accessing ${SERVICE} on ${SERVICE_HOST}:${SERVICE_NODEPORT}

N=${1:-10}
for ((i=0; i<$N; i++)); do  
	curl ${SERVICE_HOST}:${SERVICE_NODEPORT}
	echo "" ; 
done 
