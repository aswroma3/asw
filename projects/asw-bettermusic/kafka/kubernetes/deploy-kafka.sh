#!/bin/bash

echo 'Starting Kafka' 

kubectl create namespace kafka
helm install -f helm/my-kafka-values.yaml kafka helm/kafka -n kafka

# oppure: 
#helm install -f helm/my-kafka-values.yaml kafka oci://registry-1.docker.io/bitnamicharts/kafka -n kafka
# quella scaricata localmente è la versione 32.4.3 (Apache Kafka 4.0.0)
# scaricata con il comando helm pull oci://registry-1.docker.io/bitnamicharts/kafka --untar 
# questa è l'ultima versione "non sicura" (ora "legacy") 
