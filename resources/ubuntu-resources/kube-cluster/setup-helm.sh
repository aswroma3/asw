#!/bin/bash

# see https://helm.sh/docs/intro/install/
# https://github.com/helm/helm/releases

#HELM_VERSION=3.16.3
#HELM_VERSION=3.16.4
#HELM_VERSION=3.18.3
HELM_VERSION=3.18.6

# https://get.helm.sh/helm-v3.10.3-linux-amd64.tar.gz

echo "==============="
echo "installing helm"
echo "==============="

apt-get install curl gpg apt-transport-https --yes
curl -fsSL https://packages.buildkite.com/helm-linux/helm-debian/gpgkey | gpg --dearmor | tee /usr/share/keyrings/helm.gpg > /dev/null
echo "deb [signed-by=/usr/share/keyrings/helm.gpg] https://packages.buildkite.com/helm-linux/helm-debian/any/ any main" | tee /etc/apt/sources.list.d/helm-stable-debian.list
apt-get update
apt-get install helm

#curl https://baltocdn.com/helm/signing.asc | gpg --dearmor | tee /usr/share/keyrings/helm.gpg > /dev/null
#apt-get install apt-transport-https --yes
#echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/helm.gpg] https://baltocdn.com/helm/stable/debian/ all main" | tee /etc/apt/sources.list.d/helm-stable-debian.list
#apt-get update
#apt-get install helm
