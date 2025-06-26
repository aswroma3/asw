#!/bin/bash

source "/home/asw/resources/common.sh"

echo "==================================================="
echo "configuring access to kubernetes cluster (kube-dev)"
echo "==================================================="

# copia i file di configurazione da usare negli altri nodi con scp 

# KUBE_CLUSTER_MASTER_IP=10.11.1.71
KUBE_CLUSTER_MASTER_IP=$1

# see https://kubernetes.io/docs/concepts/configuration/organize-cluster-access-kubeconfig/
# altri spunti da https://jimmysong.io/en/posts/setting-up-a-kubernetes-cluster-with-vagrant/

# copio il file salvato "config" in ~/.kube : mi consentirà di 
# usare "kubectl" dialogando direttamente con l'API SERVER da qualsiasi macchina.
# Il file config è stato salvato dal nodo master e contiene le chiavi tramite le quali
# si può dialogare in modo protetto con l'api di del nodo master.

apt-get install -y sshpass

mkdir -p /home/vagrant/.kube
sshpass -p "vagrant" scp -o StrictHostKeyChecking=no vagrant@${KUBE_CLUSTER_MASTER_IP}:/home/vagrant/.kube/config /home/vagrant/.kube/config
chown $(id -u vagrant):$(id -g vagrant) /home/vagrant/.kube/config

# mkdir -p /root/.kube
# cp /home/vagrant/.kube/config /root/.kube/config
# chown $(id -u root):$(id -g root) /root/.kube/config
