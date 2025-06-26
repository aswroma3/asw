#!/bin/bash

# numero di chiamate 
N=${1:-10}

# intervallo tra una chiamata e l'altra (ms) 
DELAY=${2:-500}

# accede al servizio tramite service nodeport - sulla porta del servizio, su uno dei nodi del cluster  

SERVICE=apigateway
SERVICE_NAMESPACE=sentence

SERVICE_HOST=kube-node
# oppure 
#SERVICE_HOST=kube-cluster

# se specificato nel file di deployment dell'applicazione 
# SERVICE_NODEPORT=32081
# se assegnato in modo random, lo trovo così 
SERVICE_NODEPORT=$(kubectl get services/${SERVICE} -n ${SERVICE_NAMESPACE} -o go-template='{{(index .spec.ports 0).nodePort}}')

echo Accessing ${SERVICE} on ${SERVICE_HOST}:${SERVICE_NODEPORT}

python3 -m rest-python-client-nodeport $N $DELAY ${SERVICE_HOST}:${SERVICE_NODEPORT}

