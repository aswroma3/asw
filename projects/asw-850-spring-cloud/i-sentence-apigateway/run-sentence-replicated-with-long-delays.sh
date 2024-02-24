#!/bin/bash

# Script per avviare l'applicazione Sentence 

echo Running SENTENCE 

# Consul deve essere avviato separatamente 

echo Starting Word Services [subject*2 + verb*2 + object*2, delay=150]
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject -Dasw.sentence.wordservice.instancename=s1 -Dasw.sentence.wordservice.delay=150 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject -Dasw.sentence.wordservice.instancename=s2 -Dasw.sentence.wordservice.delay=150 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb -Dasw.sentence.wordservice.instancename=v1 -Dasw.sentence.wordservice.delay=150 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb -Dasw.sentence.wordservice.instancename=v2 -Dasw.sentence.wordservice.delay=150 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object -Dasw.sentence.wordservice.instancename=o1 -Dasw.sentence.wordservice.delay=150 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object -Dasw.sentence.wordservice.instancename=o2 -Dasw.sentence.wordservice.delay=150 word-service/build/libs/word.jar &

echo Starting Sentence Service [*2, delay=150]

java -Xms64m -Xmx128m -jar -Dasw.sentence.sentenceservice.instancename=S1 -Dasw.sentence.sentenceservice.delay=150 sentence-service/build/libs/sentence.jar &
java -Xms64m -Xmx128m -jar -Dasw.sentence.sentenceservice.instancename=S2 -Dasw.sentence.sentenceservice.delay=150 sentence-service/build/libs/sentence.jar &

sleep 10

echo Starting API Gateway [*1, listening on 8080]

java -Xms64m -Xmx128m -jar api-gateway/build/libs/apigateway.jar &

