#!/bin/bash

echo "========================================="
echo "installing calico network add-on (master)"
echo "========================================="

# https://docs.tigera.io/calico/latest/getting-started/kubernetes/

#CALICO_VERSION=3.29.1
#CALICO_VERSION=3.30.2
CALICO_VERSION=3.30.3

# usa questa pod network al posto di quella di default (192.168.0.0)
# POD_NETWORK_CIDR=10.12.0.0
POD_NETWORK_CIDR=$1

CALICO_FOLDER=/home/vagrant/kube-cluster/calico

CALICO_GITHUB_URL=https://raw.githubusercontent.com/projectcalico/calico/v${CALICO_VERSION}

mkdir -p ${CALICO_FOLDER}

curl -s -L ${CALICO_GITHUB_URL}/manifests/operator-crds.yaml \
		> ${CALICO_FOLDER}/operator-crds.yaml
curl -s -L ${CALICO_GITHUB_URL}/manifests/tigera-operator.yaml \
		> ${CALICO_FOLDER}/tigera-operator.yaml
curl -s -L ${CALICO_GITHUB_URL}/manifests/custom-resources.yaml \
        | sed s/192.168.0.0/${POD_NETWORK_CIDR}/ \
		> ${CALICO_FOLDER}/custom-resources.yaml

kubectl create -f ${CALICO_FOLDER}/operator-crds.yaml
kubectl create -f ${CALICO_FOLDER}/tigera-operator.yaml
# senza questo controllo può terminare con un errore (in alternativa, sleep 5) 
kubectl wait --for=condition=established crd/installations.operator.tigera.io 
#sleep 5
kubectl create -f ${CALICO_FOLDER}/custom-resources.yaml

# kubectl taint nodes --all node-role.kubernetes.io/control-plane-

# check: watch kubectl get pods -n calico-system
