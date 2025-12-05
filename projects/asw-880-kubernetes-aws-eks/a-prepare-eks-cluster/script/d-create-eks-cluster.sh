#!/bin/bash

echo "===================="
echo "creating eks cluster"
echo "===================="

#echo "la creazione del cluster richiede circa 15 minuti"
#echo ""

eksctl create cluster -f config/cluster-config.yaml