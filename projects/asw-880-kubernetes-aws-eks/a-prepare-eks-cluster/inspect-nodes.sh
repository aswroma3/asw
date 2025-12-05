#!/bin/bash

echo 'Inspecting kube-cluster nodes' 

kubectl get nodes 

# docker -H kube-1 ps 
# docker -H kube-2 ps 
# docker -H kube-3 ps 

