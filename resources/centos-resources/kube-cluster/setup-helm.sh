#!/bin/bash

source "/home/asw/resources/common.sh"

# see https://helm.sh/docs/intro/install/
# https://github.com/helm/helm/releases

#HELM_VERSION=3.16.3
#HELM_VERSION=3.16.4
HELM_VERSION=3.18.3

HELM_FILE=helm-v${HELM_VERSION}-linux-amd64.tar.gz 

# https://get.helm.sh/helm-v3.16.4-linux-amd64.tar.gz

echo "==============="
echo "installing helm"
echo "==============="

if ! downloadExists ${HELM_FILE} ; then
	wget -q -P ${ASW_DOWNLOADS} https://get.helm.sh/${HELM_FILE} 
fi 

mkdir -p /var/tmp/helm
tar Cxzvf /var/tmp/helm ${ASW_DOWNLOADS}/${HELM_FILE}

mv /var/tmp/helm/linux-amd64/helm /usr/bin/helm

