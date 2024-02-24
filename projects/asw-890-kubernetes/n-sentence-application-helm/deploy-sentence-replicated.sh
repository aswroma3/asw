#!/bin/bash

echo 'Starting sentence' 

kubectl create namespace sentence
helm install -f sentence/replicated-values.yaml sentence.app sentence -n sentence 


