#!/bin/bash

source "/home/asw/resources/common.sh"

echo "===================================="
echo "starting kubernetes cluster (master)"
echo "===================================="

KUBE_CLUSTER_JOIN_CMD_FILE=/home/vagrant/kube-cluster/kube_cluster_join_cmd.sh

mkdir -p /home/vagrant/kube-cluster

# usa questa pod network al posto di quella di default 
# POD_NETWORK_CIDR=10.12.0.0
POD_NETWORK_CIDR=$1

# fa copiare i file di configurazione da usare negli altri nodi con scp 
# see https://medium.com/@wso2tech/multi-node-kubernetes-cluster-with-vagrant-virtualbox-and-kubeadm-9d3eaac28b98 (punto 13) 
# ATTENZIONE: sshpass non è sicuro: https://www.tecmint.com/sshpass-non-interactive-ssh-login-shell-script-ssh-password/ 

# IP_ADDR=`ifconfig eth1 | grep inet | awk '{print $2}'| cut -f2 -d:`
IP_ADDR=$(ip address show dev eth1 | grep inet | grep -v inet6 | awk '{ print $2 }' | cut -d/ -f1)
HOST_NAME=$(hostname -s)

# avvio il cluster
# see https://kubernetes.io/docs/reference/setup-tools/kubeadm/kubeadm-init/
# see https://www.mankier.com/1/kubeadm-init
kubeadm init --cri-socket unix:/run/containerd/containerd.sock --apiserver-advertise-address=$IP_ADDR --apiserver-cert-extra-sans=$IP_ADDR  --node-name $HOST_NAME --pod-network-cidr=${POD_NETWORK_CIDR}/16

# copio il file config anche per l'utente "vagrant"
# see https://kubernetes.io/docs/concepts/configuration/organize-cluster-access-kubeconfig/
sudo --user=vagrant mkdir -p /home/vagrant/.kube
mkdir -p /root/.kube
cp -i /etc/kubernetes/admin.conf /home/vagrant/.kube/config
cp -i /etc/kubernetes/admin.conf /root/.kube/config
chown $(id -u vagrant):$(id -g vagrant) /home/vagrant/.kube/config

export KUBECONFIG=/etc/kubernetes/admin.conf

# creo il comando con token necessario per la connessione degli altri nodi al cluster
# see https://kubernetes.io/docs/reference/setup-tools/kubeadm/kubeadm-token/
kubeadm token create --print-join-command > ${KUBE_CLUSTER_JOIN_CMD_FILE}
chmod +x ${KUBE_CLUSTER_JOIN_CMD_FILE}
