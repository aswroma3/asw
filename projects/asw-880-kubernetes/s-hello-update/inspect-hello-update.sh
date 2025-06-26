#!/bin/bash

echo 'Inspecting hello' 

kubectl get services -n hello-update -o wide
kubectl get pods -n hello-update -o wide

