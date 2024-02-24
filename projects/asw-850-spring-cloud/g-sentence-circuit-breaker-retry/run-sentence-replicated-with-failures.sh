#!/bin/bash

# Script per avviare l'applicazione Sentence 

echo Running SENTENCE 

# Consul deve essere avviato separatamente 

echo Starting Word Services [subject*1 failure=15 + subject*1 failure=35 + verb*2 + object *1 + object *1 failure=25]
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject -Dasw.sentence.wordservice.instancename=s1 -Dasw.sentence.wordservice.failurerate=15 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject -Dasw.sentence.wordservice.instancename=s2 -Dasw.sentence.wordservice.failurerate=35 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb -Dasw.sentence.wordservice.instancename=v1 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb -Dasw.sentence.wordservice.instancename=v2 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object -Dasw.sentence.wordservice.instancename=o1 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object -Dasw.sentence.wordservice.instancename=o2 -Dasw.sentence.wordservice.failurerate=25 word-service/build/libs/word.jar &

echo Starting Sentence Service [*1, listening on 8080]

java -Xms64m -Xmx128m -jar -Dasw.sentence.sentenceservice.instancename=S sentence-service/build/libs/sentence.jar &

