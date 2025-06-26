#!/bin/bash

# per cancellare le immagini docker dell'applicazione dai nodi worker del cluster 

ssh vagrant@kube-2 -- exec /home/asw/projects/asw-890-kubernetes/l-sentence-kube-dockerhub/delete-local-sentence-images.sh 
ssh vagrant@kube-3 -- exec /home/asw/projects/asw-890-kubernetes/l-sentence-kube-dockerhub/delete-local-sentence-images.sh 

# ssh vagrant@kube2 -- sudo nerdctl --namespace k8s.io image ls 
# ssh vagrant@kube3 -- sudo nerdctl --namespace k8s.io image ls 
