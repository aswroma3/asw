#!/bin/bash

echo 'Halting sentence' 

kubectl delete -f sentence-application.yaml -n sentence
# le autorizzazioni servivano solo con il servizio di service discovery 
# kubectl delete -f rbac-authorizations.yaml -n sentence
kubectl delete namespace sentence

