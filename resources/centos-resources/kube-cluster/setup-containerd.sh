#!/bin/bash

source "/home/asw/resources/common.sh"

# see https://github.com/containerd/containerd/blob/main/docs/getting-started.md 
# see https://github.com/containerd/containerd/releases
# e.g. https://github.com/containerd/containerd/releases/download/v1.6.4/containerd-1.6.4-linux-amd64.tar.gz
# see https://github.com/opencontainers/runc/releases 
# e.g. https://github.com/opencontainers/runc/releases/download/v1.1.2/runc.amd64
# see https://github.com/containernetworking/plugins/releases 
# e.g. https://github.com/containernetworking/plugins/releases/download/v1.1.1/cni-plugins-linux-amd64-v1.1.1.tgz
# nerdctl è l'analogo del client docker per containerd 
# https://github.com/containerd/nerdctl 
# https://github.com/containerd/nerdctl/releases 
# https://github.com/containerd/nerdctl/releases/download/v1.1.0/nerdctl-1.1.0-linux-amd64.tar.gz

# sto provando la versione 2.0
# dà un warning nei preflight checks di kubeadm (solo su kube-1) 
# però per il resto sembra funzionare  
#CONTAINERD_VERSION=1.7.24
#CONTAINERD_VERSION=2.0.1
CONTAINERD_VERSION=2.0.2
#RUNC_VERSION=1.2.3
RUNC_VERSION=1.2.5
#CNI_VERSION=1.6.1
CNI_VERSION=1.6.2
# bisogna provare la versione 2.0
#NERDCTL_VERSION=1.7.7
#NERDCTL_VERSION=2.0.2
NERDCTL_VERSION=2.0.3

echo "====================="
echo "installing containerd"
echo "====================="

# let iptables see bridged traffic
# see https://kubernetes.io/docs/setup/production-environment/container-runtimes/

cat <<EOF | sudo tee /etc/modules-load.d/k8s.conf
overlay
br_netfilter
EOF

modprobe overlay
modprobe br_netfilter

# sysctl params required by setup, params persist across reboots
cat <<EOF | sudo tee /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-iptables  = 1
net.bridge.bridge-nf-call-ip6tables = 1
net.ipv4.ip_forward                 = 1
EOF

# Apply sysctl params without reboot
sudo sysctl --system

# runc 

if ! downloadExists runc-${RUNC_VERSION}.amd64 ; then
	wget -q -P ${ASW_DOWNLOADS} https://github.com/opencontainers/runc/releases/download/v${RUNC_VERSION}/runc.amd64
	mv ${ASW_DOWNLOADS}/runc.amd64 ${ASW_DOWNLOADS}/runc-${RUNC_VERSION}.amd64
fi
install -m 755 ${ASW_DOWNLOADS}/runc-${RUNC_VERSION}.amd64 /usr/local/sbin/runc

# cni plugin 

if ! downloadExists cni-plugins-linux-amd64-v${CNI_VERSION}.tgz ; then
	wget -q -P ${ASW_DOWNLOADS} https://github.com/containernetworking/plugins/releases/download/v${CNI_VERSION}/cni-plugins-linux-amd64-v${CNI_VERSION}.tgz
fi 

mkdir -p /opt/cni/bin
tar Cxzvf /opt/cni/bin ${ASW_DOWNLOADS}/cni-plugins-linux-amd64-v${CNI_VERSION}.tgz

# containerd 

if ! downloadExists containerd-${CONTAINERD_VERSION}-linux-amd64.tar.gz; then
	wget -q -P ${ASW_DOWNLOADS} https://github.com/containerd/containerd/releases/download/v${CONTAINERD_VERSION}/containerd-${CONTAINERD_VERSION}-linux-amd64.tar.gz
fi
tar Cxzvf /usr/local ${ASW_DOWNLOADS}/containerd-${CONTAINERD_VERSION}-linux-amd64.tar.gz
mkdir -p /usr/local/lib/systemd/system/ 
cp ${ASW_RESOURCES}/kube-cluster/containerd/containerd.service /usr/local/lib/systemd/system/

# il file config.toml è stato creato con containerd config default
# poi editato seguendo https://kubernetes.io/docs/setup/production-environment/container-runtimes/#containerd

mkdir -p /etc/containerd
cp ${ASW_RESOURCES}/kube-cluster/containerd/config.toml /etc/containerd/config.toml

systemctl daemon-reload
systemctl enable --now containerd
systemctl restart containerd

# installa anche nerdctl 

if ! downloadExists nerdctl-${NERDCTL_VERSION}-linux-amd64.tar.gz ; then
	wget -q -P ${ASW_DOWNLOADS} https://github.com/containerd/nerdctl/releases/download/v${NERDCTL_VERSION}/nerdctl-${NERDCTL_VERSION}-linux-amd64.tar.gz
fi
tar Cxzvvf /usr/local/bin ${ASW_DOWNLOADS}/nerdctl-${NERDCTL_VERSION}-linux-amd64.tar.gz

