#!/bin/bash

echo 'Starting hello' 

kubectl create namespace hello
kubectl apply -f hello-application.yaml -n hello

