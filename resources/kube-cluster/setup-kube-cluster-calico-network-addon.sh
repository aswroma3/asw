#!/bin/bash

echo "========================================="
echo "installing calico network add-on (master)"
echo "========================================="

# https://docs.tigera.io/calico/latest/getting-started/kubernetes/

#CALICO_VERSION=3.28.1
CALICO_VERSION=3.29.0

# usa questa pod network al posto di quella di default (192.168.0.0)
# POD_NETWORK_CIDR=10.12.0.0
POD_NETWORK_CIDR=$1

CALICO_FOLDER=/home/vagrant/kube-cluster/calico

mkdir -p ${CALICO_FOLDER}

curl -s -L https://raw.githubusercontent.com/projectcalico/calico/v${CALICO_VERSION}/manifests/tigera-operator.yaml \
		> ${CALICO_FOLDER}/tigera-operator.yaml
curl -s -L https://raw.githubusercontent.com/projectcalico/calico/v${CALICO_VERSION}/manifests/custom-resources.yaml \
        | sed s/192.168.0.0/${POD_NETWORK_CIDR}/ \
		> ${CALICO_FOLDER}/custom-resources.yaml

kubectl create -f ${CALICO_FOLDER}/tigera-operator.yaml
# senza questo controllo può terminare con un errore (in alternativa, sleep 5) 
kubectl wait --for=condition=established crd/installations.operator.tigera.io 
kubectl create -f ${CALICO_FOLDER}/custom-resources.yaml

# kubectl taint nodes --all node-role.kubernetes.io/control-plane-
