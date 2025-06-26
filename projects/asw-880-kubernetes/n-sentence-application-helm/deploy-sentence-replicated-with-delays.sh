#!/bin/bash

echo 'Starting sentence' 

kubectl create namespace sentence
helm install -f sentence/replicated-values.yaml -f sentence/with-delays-values.yaml sentence.app sentence -n sentence 


