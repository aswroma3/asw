#!/bin/bash

echo "***************************"
echo "* configuring eks cluster *"
echo "***************************"

# configura il cluster 
source script/b-download-aws-data.sh
source script/c-compute-cluster-config.sh

