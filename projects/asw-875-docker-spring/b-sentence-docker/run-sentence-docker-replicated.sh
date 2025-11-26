#!/bin/bash

docker network create sentence-net 

# esegue piu' container di ogni tipo (tranne consul e apigateway) 

docker run -d --network=sentence-net --name=consul docker.io/hashicorp/consul 

docker run -d --network=sentence-net -e SPRING_PROFILES_ACTIVE=subject -e ASW_SENTENCE_WORDSERVICE_INSTANCENAME=s1 --name=subject-1 sentence-word 
docker run -d --network=sentence-net -e SPRING_PROFILES_ACTIVE=verb    -e ASW_SENTENCE_WORDSERVICE_INSTANCENAME=v1 --name=verb-1 sentence-word 
docker run -d --network=sentence-net -e SPRING_PROFILES_ACTIVE=object  -e ASW_SENTENCE_WORDSERVICE_INSTANCENAME=o1 --name=object-1 sentence-word 

docker run -d --network=sentence-net -e SPRING_PROFILES_ACTIVE=subject -e ASW_SENTENCE_WORDSERVICE_INSTANCENAME=s2 --name=subject-2 sentence-word 
docker run -d --network=sentence-net -e SPRING_PROFILES_ACTIVE=verb    -e ASW_SENTENCE_WORDSERVICE_INSTANCENAME=v2 --name=verb-2 sentence-word 
docker run -d --network=sentence-net -e SPRING_PROFILES_ACTIVE=object  -e ASW_SENTENCE_WORDSERVICE_INSTANCENAME=o2 --name=object-2 sentence-word 

docker run -d --network=sentence-net -e ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S1-SYNC  --name=sentence-1 sentence-sentence 
docker run -d --network=sentence-net -e ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S2-ASYNC --name=sentence-2 sentence-sentence-async 

docker run -d --network=sentence-net -p 8080:8080 --name=apigateway sentence-apigateway 

