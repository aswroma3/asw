#!/bin/bash

# per cancellare le immagini docker dell'applicazione dai nodi worker del cluster 
# NON USARE DIRETTAMENTE 
# chiamare tramite delete-sentence-images-from-kube-cluster.sh

VERSION=2025-10

# con docker 

# docker image rm  aswroma3/sentence-sentence-kube:${VERSION}
# docker image rm  aswroma3/sentence-sentence-async-kube:${VERSION}
# docker image rm  aswroma3/sentence-word-kube:${VERSION}
# docker image rm  aswroma3/sentence-apigateway-kube:${VERSION}

# con containerd e nerdctl 

sudo nerdctl --namespace k8s.io image rm aswroma3/sentence-sentence-kube:${VERSION}
sudo nerdctl --namespace k8s.io image rm aswroma3/sentence-sentence-async-kube:${VERSION}
sudo nerdctl --namespace k8s.io image rm aswroma3/sentence-word-kube:${VERSION}
sudo nerdctl --namespace k8s.io image rm aswroma3/sentence-apigateway-kube:${VERSION}
