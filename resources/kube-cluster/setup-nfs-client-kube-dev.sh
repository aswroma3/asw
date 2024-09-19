#!/bin/bash

# see https://ubuntu.com/server/docs/service-nfs

# pensata per essere eseguita su kube-dev

echo "================================"
echo "installing nfs client (kube-dev)"
echo "================================"

apt-get update
apt-get install -y nfs-common

mkdir -p /var/kube/nfs 
#echo "10.11.1.71:/var/kube/nfs /var/kube/nfs nfs defaults 0 0" >> /etc/fstab
echo "kube-nfs:/var/kube/nfs /var/kube/nfs nfs defaults 0 0" >> /etc/fstab
mount -a
