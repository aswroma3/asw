#!/bin/bash

# per cancellare le immagini docker dell'applicazione dai nodi worker del cluster 
# NON USARE DIRETTAMENTE 
# chiamare tramite delete-hello-image-from-kube-cluster.sh

VERSION=2024-08

# con docker 

# docker image rm  aswroma3/hello-kube:${VERSION}
# docker image rm  aswroma3/hello-kube:latest
# docker image rm  aswroma3/hello-kube

# con containerd e nerdctl 

sudo nerdctl --namespace k8s.io image rm aswroma3/hello-kube:${VERSION}
sudo nerdctl --namespace k8s.io image rm aswroma3/hello-kube:latest
sudo nerdctl --namespace k8s.io image rm aswroma3/hello-kube
