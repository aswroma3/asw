#!/bin/bash

echo "************************"
echo "* deleting eks cluster *"
echo "************************"

# disinstalla l'ingress controller e untagga le reti 
source script/p-undeploy-ingress-nginx.sh
source script/q-untag-subnets.sh

# cancella il cluster 
source script/r-delete-eks-cluster.sh
