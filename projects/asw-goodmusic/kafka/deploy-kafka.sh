#!/bin/bash

echo 'Starting Kafka' 

kubectl create namespace kafka

helm install -f kafka-values.yaml -f kafka-provisioning.yaml kafka oci://registry-1.docker.io/bitnamicharts/kafka -n kafka

## oppure, per installare una versione specifica 
# helm repo add bitnami https://charts.bitnami.com/bitnami
# #helm search repo kafka -l
# helm install -f kubernetes/kafka-values.yaml kafka oci://registry-1.docker.io/bitnamicharts/kafka --version 30.1.8 -n kafka

