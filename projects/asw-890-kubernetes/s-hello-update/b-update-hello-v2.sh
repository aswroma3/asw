#!/bin/bash

echo 'Updating hello (version v2)' 

kubectl apply -f hello-update-v2.yaml -n hello-update
kubectl annotate deployment/hello-update -n hello-update kubernetes.io/change-cause="hello-update v2" 

kubectl rollout status deployment hello-update -n hello-update


