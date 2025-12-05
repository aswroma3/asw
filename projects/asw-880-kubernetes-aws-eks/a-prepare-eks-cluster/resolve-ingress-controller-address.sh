#!/bin/bash

echo "============================================"
echo "trying to resolve ingress controller address"
echo "============================================"

INGRESS_ADDRESS=$(kubectl get service --namespace ingress-nginx my-nginx-ingress-nginx-controller -o go-template='{{(index .status.loadBalancer.ingress 0).hostname}}')

echo Ingress controller address is $INGRESS_ADDRESS
echo "" 

nslookup $INGRESS_ADDRESS
