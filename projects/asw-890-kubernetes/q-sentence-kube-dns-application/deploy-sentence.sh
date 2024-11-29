#!/bin/bash

echo 'Starting sentence' 

kubectl create namespace sentence
# le autorizzazioni servivano solo con il servizio di service discovery 
# kubectl apply -f rbac-authorizations.yaml -n sentence
kubectl apply -f sentence-application.yaml -n sentence

# kubectl rollout status deployment/sentence -n sentence

