#!/bin/bash

# per elencare le immagini docker dell'applicazione dai nodi worker del cluster 

ssh vagrant@kube-2 -- sudo nerdctl --namespace k8s.io image ls 
ssh vagrant@kube-3 -- sudo nerdctl --namespace k8s.io image ls 
