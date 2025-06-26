#!/bin/bash

# per cancellare le immagini docker dell'applicazione dai nodi worker del cluster 

ssh vagrant@kube-2 -- exec /home/asw/projects/asw-890-kubernetes/a-hello-kube-dockerhub/delete-local-hello-image.sh 
ssh vagrant@kube-3 -- exec /home/asw/projects/asw-890-kubernetes/a-hello-kube-dockerhub/delete-local-hello-image.sh 
