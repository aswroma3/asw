#!/bin/bash

docker network create sentence-net 

docker run -d --network=sentence-net --name=consul docker.io/hashicorp/consul 

docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=subject" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=s1" --name=subject sentence-word 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=verb" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=v1" --name=verb sentence-word 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=object" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=o1" --name=object sentence-word 

docker run -d --network=sentence-net -e "ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S1-SYNC" --name=sentence sentence-sentence 
docker run -d --network=sentence-net -e "ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S2-ASYNC" --name=sentence-async sentence-sentence-async 

docker run -d --network=sentence-net -p 8080:8080 --name=apigateway sentence-apigateway 
