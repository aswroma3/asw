#!/bin/bash

echo "==================="
echo "tagging aws subnets"
echo "==================="

# il tagging effettuato è necessario per il corretto funzionamento dell'ingress controller 

source parameters.sh 

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

SUBNETS="${SUBNET[0]} ${SUBNET[1]} ${SUBNET[2]} ${SUBNET[3]} ${SUBNET[4]}" 
SUBNETS=$(echo $SUBNETS | tr -d '"') 

#echo $SUBNETS

aws ec2 create-tags --region $AWS_REGION \
    --resources $SUBNETS \
    --tags Key=kubernetes.io/cluster/$CLUSTER_NAME,Value=shared
	
aws ec2 create-tags --region $AWS_REGION \
    --resources $SUBNETS \
    --tags Key=kubernetes.io/role/elb,Value=1 
