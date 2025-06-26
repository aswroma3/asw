#!/bin/bash

echo 'Starting hello (version v1)' 

kubectl create namespace hello-update  
kubectl apply -f hello-update-v1.yaml -n hello-update 
kubectl annotate deployment/hello-update -n hello-update kubernetes.io/change-cause="hello-update v1" 

kubectl rollout status deployment hello-update -n hello-update

