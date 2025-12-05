#!/bin/bash

echo "========================="
echo "undeploying ingress-nginx"
echo "========================="

helm uninstall my-nginx --namespace ingress-nginx 
