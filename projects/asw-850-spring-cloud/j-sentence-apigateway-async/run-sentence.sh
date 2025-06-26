#!/bin/bash

# Script per avviare l'applicazione Sentence 

echo Running SENTENCE 

# Consul deve essere avviato separatamente 

echo Starting Word Services [subject*1 + verb*1 + object*1]
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object word-service/build/libs/word.jar &

echo Starting Sentence Service [*2, 1 sync + 1 async]

java -Xms64m -Xmx128m -jar -Dasw.sentence.sentenceservice.instancename=S1-SYNC sentence-service/build/libs/sentence.jar &
java -Xms64m -Xmx128m -jar -Dasw.sentence.sentenceservice.instancename=S2-ASYNC sentence-service-async/build/libs/sentence-async.jar &

sleep 10

echo Starting API Gateway [*1, listening on 8080]

java -Xms64m -Xmx128m -jar api-gateway/build/libs/apigateway.jar &
