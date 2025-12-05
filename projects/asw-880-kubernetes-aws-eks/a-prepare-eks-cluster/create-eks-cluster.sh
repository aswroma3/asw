#!/bin/bash

echo "************************"
echo "* creating eks cluster *"
echo "************************"

echo "attenzione, la creazione del cluster richiede circa 15 minuti"
echo ""

# il cluster deve essere stato già configurato 

# crea il cluster 
source script/d-create-eks-cluster.sh

# configura l'accesso al cluster 
source script/e-configure-aws-kubectl.sh

# rilascia l'ingress controller nel cluster 
source script/f-tag-subnets.sh
source script/g-deploy-ingress-nginx.sh
