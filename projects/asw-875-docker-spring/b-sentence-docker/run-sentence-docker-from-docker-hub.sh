#!/bin/bash

DOCKERHUB_USERNAME=aswroma3 
VERSION=2024-10

docker network create sentence-net 

docker run -d --network=sentence-net --name=consul docker.io/hashicorp/consul 

docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=subject" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=s1" --name=subject ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=verb" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=v1" --name=verb ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=object" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=o1" --name=object ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 

docker run -d --network=sentence-net -e "ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S1-SYNC" --name=sentence ${DOCKERHUB_USERNAME}/sentence-sentence:${VERSION} 
docker run -d --network=sentence-net -e "ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S2-ASYNC" --name=sentence-async ${DOCKERHUB_USERNAME}/sentence-sentence-async:${VERSION} 

docker run -d --network=sentence-net -p 8080:8080 --name=apigateway ${DOCKERHUB_USERNAME}/sentence-apigateway:${VERSION} 

