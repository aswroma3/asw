#!/bin/bash

#eksctl utils describe-stacks --region=us-east-1 --cluster=cluster-asw --output json

#aws eks describe-cluster --name cluster-asw --region us-east-1 --output yaml

aws eks describe-cluster --name cluster-asw --region us-east-1
