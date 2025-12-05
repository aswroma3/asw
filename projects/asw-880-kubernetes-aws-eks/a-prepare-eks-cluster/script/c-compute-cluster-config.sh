#!/bin/bash

echo "============================="
echo "preparing cluster config file"
echo "============================="

source parameters.sh 

#CLUSTER_NAME=cluster-asw
#AWS_REGION=us-east-1
#K8S_VERSION='"1.34"'

NODE_ROLE=$(cat config/roles.json | jq .Roles[].Arn | grep LabEksNodeRole) 
CLUSTER_ROLE=$(cat config/roles.json | jq .Roles[].Arn | grep LabEksClusterRole) 

VPC=$(cat config/vpcs.json | jq .Vpcs[0].VpcId)

# da quanto capisco, l'account ha 6 sottoreti, 
# ma per il cluster ne vanno utilizzate solo 5, 
# e precisamente quelle che NON sono nella zona us-east-1e 

declare -a SUBNET 
declare -a AZ
declare -i j 

j=0 
for i in $(seq 0 5);
do
    SNI=$(cat config/subnets.json | jq .Subnets[$i].SubnetId)
	AZI=$(cat config/subnets.json | jq .Subnets[$i].AvailabilityZone | tr -d '"')
    if [ ${AZI} != "us-east-1e" ]; then 
		SUBNET[$j]=${SNI}
		AZ[$j]=${AZI}
		j=$((j+1))
	fi
done

# le sottoreti da utilizzare sono quelle con indice tra 0 e 4

#echo $CLUSTER_NAME
#echo $AWS_REGION
#echo $NODE_ROLE
#echo $CLUSTER_ROLE
#echo $VPC
#for i in $(seq 0 4);
#do
#    echo ${SUBNET[$i]}
#    echo ${AZ[$i]}
#done

cat <<EOF > config/cluster-config.yaml
apiVersion: eksctl.io/v1alpha5
kind: ClusterConfig

metadata:
  name: $CLUSTER_NAME
  region: $AWS_REGION
  version: $K8S_VERSION

autoModeConfig:
  enabled: true
  nodeRoleARN: $NODE_ROLE
  
iam: 
  serviceRoleARN: $CLUSTER_ROLE

vpc: 
  id: $VPC
  subnets: 
    public: 
      ${AZ[0]}:  
        id: ${SUBNET[0]}
      ${AZ[1]}:  
        id: ${SUBNET[1]}
      ${AZ[2]}:  
        id: ${SUBNET[2]}
      ${AZ[3]}:  
        id: ${SUBNET[3]}
      ${AZ[4]}:  
        id: ${SUBNET[4]}
EOF

