#!/bin/bash

# see https://ubuntu.com/server/docs/service-nfs

# pensata per essere eseguita su kube-2 e kube-3

echo "=============================="
echo "installing nfs client (worker)"
echo "=============================="

apt-get update
apt-get install -y nfs-common

