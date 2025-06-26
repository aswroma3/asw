#!/bin/bash

echo 'Halting sentence' 

helm uninstall sentence.app -n sentence

kubectl delete namespace sentence

