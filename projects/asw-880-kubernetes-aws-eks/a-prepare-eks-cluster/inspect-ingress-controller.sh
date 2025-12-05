#!/bin/bash

kubectl get pods --namespace ingress-nginx -o wide
kubectl get service --namespace ingress-nginx my-nginx-ingress-nginx-controller --output wide 