#!/bin/bash

echo "===================="
echo "deleting eks cluster"
echo "===================="

eksctl delete cluster -f config/cluster-config.yaml