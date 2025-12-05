#!/bin/bash

echo 'Inspecting sentence' 

kubectl get services -n sentence -o wide
kubectl get pods -n sentence -o wide
kubectl get ingress -n sentence -o wide
