#!/bin/bash

kubectl delete -f hello-application.yaml -n hello
kubectl delete namespace hello

