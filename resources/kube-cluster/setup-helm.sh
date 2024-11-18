#!/bin/bash

# see https://helm.sh/docs/intro/install/
# https://github.com/helm/helm/releases

#HELM_VERSION=3.15.4
HELM_VERSION=3.16.3

# https://get.helm.sh/helm-v3.10.3-linux-amd64.tar.gz

echo "==============="
echo "installing helm"
echo "==============="

curl https://baltocdn.com/helm/signing.asc | gpg --dearmor | tee /usr/share/keyrings/helm.gpg > /dev/null
apt-get install apt-transport-https --yes
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/helm.gpg] https://baltocdn.com/helm/stable/debian/ all main" | tee /etc/apt/sources.list.d/helm-stable-debian.list
apt-get update
apt-get install helm
