#!/bin/bash

DOCKERHUB_USERNAME=aswroma3 
VERSION=2025-09

docker tag sentence-sentence ${DOCKERHUB_USERNAME}/sentence-sentence:${VERSION}
docker tag sentence-sentence-async ${DOCKERHUB_USERNAME}/sentence-sentence-async:${VERSION}
docker tag sentence-word ${DOCKERHUB_USERNAME}/sentence-word:${VERSION}
docker tag sentence-apigateway ${DOCKERHUB_USERNAME}/sentence-apigateway:${VERSION} 
docker tag sentence-sentence ${DOCKERHUB_USERNAME}/sentence-sentence:latest
docker tag sentence-sentence-async ${DOCKERHUB_USERNAME}/sentence-sentence-async:latest
docker tag sentence-word ${DOCKERHUB_USERNAME}/sentence-word:latest
docker tag sentence-apigateway ${DOCKERHUB_USERNAME}/sentence-apigateway:latest 

docker push ${DOCKERHUB_USERNAME}/sentence-sentence:${VERSION}
docker push ${DOCKERHUB_USERNAME}/sentence-sentence-async:${VERSION}
docker push ${DOCKERHUB_USERNAME}/sentence-word:${VERSION}
docker push ${DOCKERHUB_USERNAME}/sentence-apigateway:${VERSION} 
docker push ${DOCKERHUB_USERNAME}/sentence-sentence:latest
docker push ${DOCKERHUB_USERNAME}/sentence-sentence-async:latest
docker push ${DOCKERHUB_USERNAME}/sentence-word:latest
docker push ${DOCKERHUB_USERNAME}/sentence-apigateway:latest 
