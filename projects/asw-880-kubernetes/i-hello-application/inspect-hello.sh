#!/bin/bash

echo 'Inspecting hello' 

kubectl get services -n hello -o wide
kubectl get pods -n hello -o wide

