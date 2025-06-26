#!/bin/bash

DOCKERHUB_USERNAME=aswroma3 
VERSION=2024-10

docker network create sentence-net 

docker run -d --network=sentence-net --name=consul docker.io/hashicorp/consul 

docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=subject" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=s1" -e "ASW_SENTENCE_WORDSERVICE_DELAY=50" --name=subject ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=verb" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=v1" -e "ASW_SENTENCE_WORDSERVICE_DELAY=50" --name=verb ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=object" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=o1" -e "ASW_SENTENCE_WORDSERVICE_DELAY=50" --name=object ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 

docker run -d --network=sentence-net -e "ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S1-SYNC" -e "ASW_SENTENCE_SENTENCESERVICE_DELAY=50" --name=sentence ${DOCKERHUB_USERNAME}/sentence-sentence:${VERSION} 
docker run -d --network=sentence-net -e "ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S2-ASYNC" -e "ASW_SENTENCE_SENTENCESERVICE_DELAY=50" --name=sentence-async ${DOCKERHUB_USERNAME}/sentence-sentence-async:${VERSION} 

docker run -d --network=sentence-net -p 8080:8080 --name=apigateway ${DOCKERHUB_USERNAME}/sentence-apigateway:${VERSION} 

