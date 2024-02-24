#!/bin/bash

SERVICE=${1:-sentence}

echo 'Inspecting logs for' $SERVICE

POD=$(kubectl get pods -n sentence | grep $SERVICE | awk '{ print $1 }' | cut -d/ -f1)

kubectl logs $POD -n sentence 

