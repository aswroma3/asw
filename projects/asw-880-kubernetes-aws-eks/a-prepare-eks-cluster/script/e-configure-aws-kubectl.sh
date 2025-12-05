#!/bin/bash

echo "======================="
echo "configuring aws kubectl"
echo "======================="

source parameters.sh 

rm -rf /home/vagrant/.kube/

aws eks update-kubeconfig --region $AWS_REGION --name $CLUSTER_NAME

# kubectl get nodes 