#!/bin/bash

echo 'Scaling hello up to 3 replicas' 

kubectl scale deployment/hello-deploy --replicas=3 -n hello

