#!/bin/bash

# see https://ubuntu.com/server/docs/service-nfs

# pensata per essere eseguita su kube-1 
# (oppure si potrebbe utilizzare un nfs server apposito) 

echo "=============================="
echo "installing nfs server (kube-1)"
echo "=============================="

apt-get update
apt-get install -y nfs-kernel-server

mkdir -p /var/kube/nfs 
echo "/var/kube/nfs *(rw,sync,no_subtree_check,no_root_squash)" >> /etc/exports
exportfs -a
