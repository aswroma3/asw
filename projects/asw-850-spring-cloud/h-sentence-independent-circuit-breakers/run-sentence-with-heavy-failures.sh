#!/bin/bash

# Script per avviare l'applicazione Sentence 

echo Running SENTENCE 

# Consul deve essere avviato separatamente 

echo Starting Word Services [subject*1 failureRate=75 + verb*1 failureRate=25 + object*1 failureRate=25]
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject -Dasw.sentence.wordservice.instancename=s1 -Dasw.sentence.wordservice.failurerate=75 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb -Dasw.sentence.wordservice.instancename=v1 -Dasw.sentence.wordservice.failurerate=25 word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object -Dasw.sentence.wordservice.instancename=o1 -Dasw.sentence.wordservice.failurerate=25 word-service/build/libs/word.jar &

echo Starting Sentence Service [*1, listening on 8080]

java -Xms64m -Xmx128m -jar -Dasw.sentence.sentenceservice.instancename=S sentence-service/build/libs/sentence.jar &

