#!/bin/bash

# Script per avviare l'applicazione Sentence 

echo Running SENTENCE 

# Consul deve essere avviato separatamente 

echo Starting Word Services [subject*1 + verb*1 + object*1, delay=50]
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject -Dasw.sentence.wordservice.instancename=s1 -Dasw.sentence.wordservice.delay=50 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb -Dasw.sentence.wordservice.instancename=v1 -Dasw.sentence.wordservice.delay=50 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object -Dasw.sentence.wordservice.instancename=o1 -Dasw.sentence.wordservice.delay=50 word-service/build/libs/word.jar &

echo Starting Sentence Service [*1, listening on 8080, delay=50]

java -Xms64m -Xmx128m -jar -Dasw.sentence.sentenceservice.instancename=S -Dasw.sentence.sentenceservice.delay=50 sentence-service/build/libs/sentence.jar &

