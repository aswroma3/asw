#!/bin/bash

echo "==================="
echo "configuring aws cli"
echo "==================="

# bisogna prima aver 
# - effettuato l'accesso al learner lab 
# - aver copiato le credenziali in aws_credentials

rm -rf /home/vagrant/.aws/

if [ -e aws_credentials ] 
then
	# aws_credentials exists
	if [ -e /home/vagrant/.aws/credentials ]
	then 
		rm -rf /home/vagrant/.aws/credentials
	fi
    mkdir /home/vagrant/.aws 
    cp aws_credentials /home/vagrant/.aws/credentials 
    # aws sts get-caller-identity  
else 
    # aws_credentials does not exist
	echo "effettua l'accesso all'AWS Learner Lab"
	echo "e copia le tue credenziali AWS CLI (disponibili seguendo il link AWS Details) nel file aws_credentials"
	echo "poi esegui nuovamente questo script" 
fi 

