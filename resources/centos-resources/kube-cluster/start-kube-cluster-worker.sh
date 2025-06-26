#!/bin/bash

source "/home/asw/resources/common.sh"

echo "==================================="
echo "joining kubernetes cluster (worker)"
echo "==================================="

# copia i file di configurazione da usare negli altri nodi con scp 
# see https://medium.com/@wso2tech/multi-node-kubernetes-cluster-with-vagrant-virtualbox-and-kubeadm-9d3eaac28b98 (punto 13) 

KUBE_CLUSTER_JOIN_CMD_FILE=/home/vagrant/kube-cluster/kube_cluster_join_cmd.sh
LOCAL_JOIN_CMD_FILE=/home/vagrant/kube_cluster_join_cmd.sh

# KUBE_CLUSTER_MASTER_IP=10.11.1.71
KUBE_CLUSTER_MASTER_IP=$1

# see https://kubernetes.io/docs/reference/setup-tools/kubeadm/kubeadm-join/
# see https://kubernetes.io/docs/concepts/configuration/organize-cluster-access-kubeconfig/
# altri spunti da https://jimmysong.io/en/posts/setting-up-a-kubernetes-cluster-with-vagrant/

# copio dal nodo master lo script per il join del nodo al cluster e lo eseguo
yum -y install sshpass
sshpass -p "vagrant" scp -o StrictHostKeyChecking=no vagrant@${KUBE_CLUSTER_MASTER_IP}:${KUBE_CLUSTER_JOIN_CMD_FILE} ${LOCAL_JOIN_CMD_FILE}
chmod +x ${LOCAL_JOIN_CMD_FILE}
sh ${LOCAL_JOIN_CMD_FILE}

# copio il file config in locale, per poter utilizzare kubectl come se fossi nel nodo master
mkdir -p /home/vagrant/.kube
sshpass -p "vagrant" scp -o StrictHostKeyChecking=no vagrant@${KUBE_CLUSTER_MASTER_IP}:/home/vagrant/.kube/config /home/vagrant/.kube/config
chown $(id -u vagrant):$(id -g vagrant) /home/vagrant/.kube/config

mkdir -p /root/.kube
cp /home/vagrant/.kube/config /root/.kube/config
chown $(id -u root):$(id -g root) /root/.kube/config
