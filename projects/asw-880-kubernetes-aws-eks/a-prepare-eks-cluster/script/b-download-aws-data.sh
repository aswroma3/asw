#!/bin/bash

echo "===================="
echo "downloading aws data"
echo "===================="

source parameters.sh 

mkdir -p config/
aws iam list-roles > config/roles.json
aws ec2 describe-vpcs --region $AWS_REGION > config/vpcs.json 
aws ec2 describe-subnets --region $AWS_REGION > config/subnets.json

