#!/bin/bash

echo 'Starting sentence' 

kubectl apply -f rbac-authorizations.yaml

kubectl create namespace sentence
kubectl apply -f sentence-application-multi.yaml -n sentence

# kubectl rollout status deployment/sentence -n sentence

