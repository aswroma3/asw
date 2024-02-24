#!/bin/bash

# (2) crea l'immagine docker  
# prerequisito: (1) build del progetto Java

DOCKERHUB_USERNAME=aswroma3 
IMAGE_NAME=lucky-word

docker image build --rm -t ${DOCKERHUB_USERNAME}/${IMAGE_NAME} . 
