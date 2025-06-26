#!/bin/bash

source "/home/asw/resources/common.sh"

# see https://docs.docker.com/engine/install/centos/

# per sovrascrivere la configurazione di docker, 
# invocare uno script separato come prepare-docker-configuration.sh 

# set up Docker constants 
#DOCKER_VERSION=3:27.4.1-1.el9
#DOCKER_VERSION=3:28.0.0-1.el9
DOCKER_VERSION=3:28.2.2-1.el9

#DOCKER_CLI_VERSION=1:27.4.1-1.el9
#DOCKER_CLI_VERSION=1:28.0.0-1.el9
DOCKER_CLI_VERSION=1:28.2.2-1.el9

#CONTAINERD_VERSION=1.7.24-3.1.el9
#CONTAINERD_VERSION=1.7.25-3.1.el9
CONTAINERD_VERSION=1.7.27-3.1.el9

#DOCKER_COMPOSE_VERSION=2.32.1-1.el9
#DOCKER_COMPOSE_VERSION=2.33.0-1.el9
DOCKER_COMPOSE_VERSION=2.36.2-1.el9

#DOCKER_BUILDX_VERSION=0.19.3-1.el9
#DOCKER_BUILDX_VERSION=0.21.0-1.el9
DOCKER_BUILDX_VERSION=0.24.0-1.el9

VAGRANT_USER=vagrant

echo "================="
echo "installing docker"
echo "================="

# uninstall old version 
#dnf remove docker \
#                  docker-client \
#                  docker-client-latest \
#                  docker-common \
#                  docker-latest \
#                  docker-latest-logrotate \
#                  docker-logrotate \
#                  docker-engine

# prepara l'installazione di docker 
# copia i file HOSTS.CONF e DAEMON.JSON per sovrascrivere la configurazione di docker 
# - il primo, per abilitare la comunicazione remota del nodo 
# - il secondo, per ulteriori configurazioni (ad es., per abilitare eventuali registry insicuri) (non usato) 
# copia /etc/systemd/system/docker.service.d/hosts.conf
mkdir -p /etc/systemd/system/docker.service.d
cp ${ASW_RESOURCES}/docker/docker.service.d/hosts.conf /etc/systemd/system/docker.service.d/hosts.conf
chmod a-x /etc/systemd/system/docker.service.d/hosts.conf

# setup the repository 
dnf -y install dnf-plugins-core
dnf config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

# per vedere le versioni disponibili 
# dnf list docker-ce --showduplicates | sort -r 

# install docker 
dnf -y install 	docker-ce-${DOCKER_VERSION} docker-ce-cli-${DOCKER_CLI_VERSION} \
				containerd.io-${CONTAINERD_VERSION} \
				docker-buildx-plugin-${DOCKER_BUILDX_VERSION} docker-compose-plugin-${DOCKER_COMPOSE_VERSION}

# start docker engine 
systemctl enable --now docker

# abilita l'uso di docker da parte di utenti non root 

# groupadd docker
usermod -aG docker ${VAGRANT_USER}

newgrp docker

# Alcuni esempi per verificare l'installazione 
# docker run hello-world
# docker run docker/whalesay cowsay Hello, world! (no, l'immagine ora è deprecata)
# docker run -it ubuntu bash

##### configure docker to start on boot 

systemctl enable docker.service
systemctl enable containerd.service
systemctl daemon-reload
systemctl restart docker.service


