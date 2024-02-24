#!/bin/bash

DOCKERHUB_USERNAME=aswroma3 
VERSION=2023-10

docker image rm sentence-sentence
docker image rm sentence-sentence-async
docker image rm sentence-word
docker image rm sentence-apigateway

docker image rm ${DOCKERHUB_USERNAME}/sentence-sentence:${VERSION}
docker image rm ${DOCKERHUB_USERNAME}/sentence-sentence-async:${VERSION}
docker image rm ${DOCKERHUB_USERNAME}/sentence-word:${VERSION}
docker image rm ${DOCKERHUB_USERNAME}/sentence-apigateway:${VERSION} 
docker image rm ${DOCKERHUB_USERNAME}/sentence-sentence:latest
docker image rm ${DOCKERHUB_USERNAME}/sentence-sentence-async:latest
docker image rm ${DOCKERHUB_USERNAME}/sentence-word:latest
docker image rm ${DOCKERHUB_USERNAME}/sentence-apigateway:latest 



