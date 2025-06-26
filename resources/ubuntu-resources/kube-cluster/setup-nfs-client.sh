#!/bin/bash

# see https://ubuntu.com/server/docs/service-nfs

# opzionale, solo per kube-dev 
MOUNT_NFS=$1

# pensata per essere eseguita su kube-2, kube-3 e kube-dev

echo "===================================="
echo "installing nfs client (kube-2/3/dev)"
echo "===================================="

apt-get update
apt-get install -y nfs-common

# monta nfs solo su kube-dev 
if [ ! -z ${MOUNT_NFS} ]; then 
	mkdir -p /var/kube/nfs 
	echo "kube-nfs:/var/kube/nfs /var/kube/nfs nfs defaults 0 0" >> /etc/fstab
	mount -a
fi
