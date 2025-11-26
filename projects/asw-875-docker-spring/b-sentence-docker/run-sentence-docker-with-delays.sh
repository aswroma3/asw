#!/bin/bash

docker network create sentence-net 

docker run -d --network=sentence-net --name=consul docker.io/hashicorp/consul 

docker run -d --network=sentence-net -e SPRING_PROFILES_ACTIVE=subject -e ASW_SENTENCE_WORDSERVICE_INSTANCENAME=s1 -e ASW_SENTENCE_WORDSERVICE_DELAY=50 --name=subject sentence-word 
docker run -d --network=sentence-net -e SPRING_PROFILES_ACTIVE=verb    -e ASW_SENTENCE_WORDSERVICE_INSTANCENAME=v1 -e ASW_SENTENCE_WORDSERVICE_DELAY=50 --name=verb sentence-word 
docker run -d --network=sentence-net -e SPRING_PROFILES_ACTIVE=object  -e ASW_SENTENCE_WORDSERVICE_INSTANCENAME=o1 -e ASW_SENTENCE_WORDSERVICE_DELAY=50 --name=object sentence-word 

docker run -d --network=sentence-net -e ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S1-SYNC  -e ASW_SENTENCE_SENTENCESERVICE_DELAY=50 --name=sentence sentence-sentence 
docker run -d --network=sentence-net -e ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S2-ASYNC -e ASW_SENTENCE_SENTENCESERVICE_DELAY=50 --name=sentence-async sentence-sentence-async 

docker run -d --network=sentence-net -p 8080:8080 --name=apigateway sentence-apigateway 
