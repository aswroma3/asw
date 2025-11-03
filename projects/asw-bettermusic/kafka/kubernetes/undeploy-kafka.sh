#!/bin/bash

echo 'Halting Kafka' 

helm delete kafka -n kafka
kubectl delete namespace kafka

