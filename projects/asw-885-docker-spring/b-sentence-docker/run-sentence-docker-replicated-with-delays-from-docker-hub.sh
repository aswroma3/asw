#!/bin/bash

DOCKERHUB_USERNAME=aswroma3 
VERSION=2023-10

docker network create sentence-net 

# esegue piu' container di ogni tipo (tranne consul e apigateway) 

#!/bin/bash

docker network create sentence-net 

docker run -d --network=sentence-net --name=consul docker.io/hashicorp/consul 

docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=subject" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=s1" -e "ASW_SENTENCE_WORDSERVICE_DELAY=50" --name=subject-1 ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=verb" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=v1" -e "ASW_SENTENCE_WORDSERVICE_DELAY=50" --name=verb-1 ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=object" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=o1" -e "ASW_SENTENCE_WORDSERVICE_DELAY=50" --name=object-1 ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 

docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=subject" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=s2" -e "ASW_SENTENCE_WORDSERVICE_DELAY=50" --name=subject-2 ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=verb" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=v2" -e "ASW_SENTENCE_WORDSERVICE_DELAY=50" --name=verb-2 ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 
docker run -d --network=sentence-net -e "SPRING_PROFILES_ACTIVE=object" -e "ASW_SENTENCE_WORDSERVICE_INSTANCENAME=o2" -e "ASW_SENTENCE_WORDSERVICE_DELAY=50" --name=object-2 ${DOCKERHUB_USERNAME}/sentence-word:${VERSION} 

docker run -d --network=sentence-net -e "ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S1-SYNC" -e "ASW_SENTENCE_SENTENCESERVICE_DELAY=50" --name=sentence-1 ${DOCKERHUB_USERNAME}/sentence-sentence:${VERSION} 
docker run -d --network=sentence-net -e "ASW_SENTENCE_SENTENCESERVICE_INSTANCENAME=S2-ASYNC" -e "ASW_SENTENCE_SENTENCESERVICE_DELAY=50" --name=sentence-2 ${DOCKERHUB_USERNAME}/sentence-sentence-async:${VERSION} 

docker run -d --network=sentence-net -p 8080:8080 --name=apigateway ${DOCKERHUB_USERNAME}/sentence-apigateway:${VERSION} 

