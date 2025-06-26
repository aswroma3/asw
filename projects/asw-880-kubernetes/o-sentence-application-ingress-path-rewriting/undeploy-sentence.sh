#!/bin/bash

echo 'Halting sentence' 

kubectl delete -f sentence-ingress.yaml -n sentence
kubectl delete -f sentence-application.yaml -n sentence
kubectl delete -f rbac-authorizations.yaml -n sentence
kubectl delete namespace sentence

