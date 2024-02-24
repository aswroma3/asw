#!/bin/bash

# per creare l'immagine docker e salvarla su docker hub 

# prerequisito: 1) build del progetto java e 2) aver eseguito il docker login su docker hub 

VERSION=2023-10

docker build --rm -t hello-kube . 
docker tag hello-kube aswroma3/hello-kube:${VERSION}
docker tag hello-kube aswroma3/hello-kube:latest
docker push aswroma3/hello-kube:${VERSION}
docker push aswroma3/hello-kube:latest
