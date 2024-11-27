#!/bin/bash


kubectl delete -f sentence-application-multi.yaml -n sentence
kubectl delete -f rbac-authorizations.yaml -n sentence
kubectl delete namespace sentence

