#!/bin/bash

# see https://www.server-world.info/en/note?os=CentOS_Stream_9&p=nfs&f=1

# pensata per essere eseguita su kube-1 
# (oppure si potrebbe utilizzare un nfs server apposito) 

echo "=============================="
echo "installing nfs server (kube-1)"
echo "=============================="

dnf -y install nfs-utils

mkdir -p /var/kube/nfs 
echo "/var/kube/nfs *(rw,sync,no_subtree_check,no_root_squash)" >> /etc/exports
systemctl enable --now rpcbind nfs-server
#exportfs -a
