#!/bin/bash

echo 'Halting sentence' 

kubectl delete -f sentence-application-multi.yaml -n sentence
kubectl delete namespace sentence

kubectl delete -f rbac-authorizations.yaml
