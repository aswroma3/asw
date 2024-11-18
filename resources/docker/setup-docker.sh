#!/bin/bash

source "/home/asw/resources/common.sh"

# see https://docs.docker.com/engine/install/ubuntu/

# per sovrascrivere la configurazione di docker, 
# invocare uno script separato come prepare-docker-configuration.sh 

# set up Docker constants 
#DOCKER_VERSION=5:27.1.2-1~ubuntu.24.04~noble
#DOCKER_VERSION=5:27.2.1-1~ubuntu.24.04~noble
DOCKER_VERSION=5:27.3.1-1~ubuntu.24.04~noble

# Per vedere le versioni disponibili 
# apt-get update && apt-cache madison docker-ce
# oppure https://download.docker.com/linux/ubuntu/dists/noble/pool/stable/amd64/ per ubuntu 24.04

#CONTAINERD_VERSION=1.7.20-1
#CONTAINERD_VERSION=1.7.22-1
CONTAINERD_VERSION=1.7.23-1
#DOCKER_COMPOSE_VERSION=2.29.1-1~ubuntu.24.04~noble
#DOCKER_COMPOSE_VERSION=2.29.2-1~ubuntu.24.04~noble
DOCKER_COMPOSE_VERSION=2.29.7-1~ubuntu.24.04~noble

echo "================="
echo "installing docker"
echo "================="

# per Ubuntu 20.04 LTS e successivi 
VAGRANT_USER=vagrant 

# Update the apt package index 
apt-get update 

# Install packages to allow apt to use a repository over HTTPS:
apt-get -y install \
    ca-certificates \
    curl 
#	gnupg \
#    lsb-release
# apt-transport-https

# Add Docker’s official GPG key: 
install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
chmod a+r /etc/apt/keyrings/docker.asc
 
# Set up the stable repository
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  tee /etc/apt/sources.list.d/docker.list > /dev/null

# Update (again) the apt package index 
apt-get update 

# Install the latest version of Docker CE 
# sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

# Per vedere le versioni disponibili, e.g. 
# apt-cache madison docker-ce

# Per installare una versione specifica (raccomandato in produzione) 
apt-get -y install docker-ce=${DOCKER_VERSION} docker-ce-cli=${DOCKER_VERSION} containerd.io=${CONTAINERD_VERSION} docker-buildx-plugin docker-compose-plugin=${DOCKER_COMPOSE_VERSION}

# Alcuni esempi per verificare l'installazione 
# docker run hello-world
# docker run docker/whalesay cowsay Hello, world! (no, l'immagine ora è deprecata)
# docker run -it ubuntu bash

##### post-installation 

# non serve: groupadd: group 'docker' already exists
# groupadd docker

# abilita l'utente vagrant 
usermod -aG docker ${VAGRANT_USER}
# Remember to log out and back in for this to take effect! oppure 
newgrp docker

##### configure docker to start on boot 

### Su Ubuntu 16.04 e superiori 
systemctl enable docker.service
systemctl enable containerd.service
systemctl daemon-reload
systemctl restart docker.service

