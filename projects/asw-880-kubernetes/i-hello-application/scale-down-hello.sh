#!/bin/bash

echo 'Scaling hello down to 1 replica' 

kubectl scale deployment/hello-deploy --replicas=1 -n hello

