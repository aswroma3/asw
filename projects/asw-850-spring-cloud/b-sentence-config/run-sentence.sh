#!/bin/bash

# Script per avviare l'applicazione Sentence 

echo Running SENTENCE 

echo Starting Word Services [subject*1 + verb*1 + object*1]
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=subject word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=verb word-service/build/libs/word.jar &
java -Xms64m -Xmx128m -jar -Dspring.profiles.active=object word-service/build/libs/word.jar &

echo Starting Sentence Service [*1, listening on 8080]

java -Xms64m -Xmx128m -jar sentence-service/build/libs/sentence.jar &

