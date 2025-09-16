#!/bin/bash

echo "===================================="
echo "installing kubernetes (with kubeadm)"
echo "===================================="

# https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/

#KUBERNETES_VERSION=1.33
KUBERNETES_VERSION=1.34

# Set SELinux in permissive mode (effectively disabling it)
setenforce 0
sed -i 's/^SELINUX=enforcing$/SELINUX=permissive/' /etc/selinux/config

# This overwrites any existing configuration in /etc/yum.repos.d/kubernetes.repo
cat <<EOF | tee /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=https://pkgs.k8s.io/core:/stable:/v${KUBERNETES_VERSION}/rpm/
enabled=1
gpgcheck=1
gpgkey=https://pkgs.k8s.io/core:/stable:/v${KUBERNETES_VERSION}/rpm/repodata/repomd.xml.key
exclude=kubelet kubeadm kubectl cri-tools kubernetes-cni
EOF

# installing kubeadm, kubelet and kubectl

yum install -y kubelet kubeadm kubectl --disableexcludes=kubernetes

systemctl enable --now kubelet

# l'applicativo kubelet richiede lo swapoff
# see https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/
# see https://askubuntu.com/questions/214805/how-do-i-disable-swap

swapoff -a
# setto lo swapoff anche per il post reboot
sed -i '/swap/ s/^\(.*\)$/#\1/g' /etc/fstab

# risolvo l'ip della macchina attuale
# ubuntu 24.04 
IP_ADDR=$(ip address show dev eth1 | grep inet | grep -v inet6 | awk '{ print $2 }' | cut -d/ -f1)

# see https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/kubelet-integration/
# creo il file /etc/default/kubelet , se inesistente
if [[ ! -e /etc/default/kubelet ]]; then
    touch /etc/default/kubelet
fi

# see https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/kubelet-integration/
# see https://kubernetes.io/docs/tasks/administer-cluster/reserve-compute-resources/
# aggiungo informazioni aggiuntive per la configurazione di kubelet e 
# consentire la creazione corretta del cluster (è l'equivalente di override.conf per il demone docker)
# v1: echo -e "KUBELET_EXTRA_ARGS=--node-ip=$IP_ADDR --cgroup-driver=cgroupfs" >> /etc/default/kubelet
echo -e "KUBELET_EXTRA_ARGS=--node-ip=$IP_ADDR --cgroup-driver=systemd" >> /etc/default/kubelet

systemctl daemon-reload
systemctl restart kubelet