#!/bin/bash

echo 'Starting sentence' 

kubectl create namespace sentence
helm install sentence.app sentence -n sentence 


