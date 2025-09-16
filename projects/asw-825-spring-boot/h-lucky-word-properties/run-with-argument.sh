#!/bin/bash

# Script per avviare il servizio lucky-word 

echo Running with lucky word passed as an argument

java -jar build/libs/lucky-word.jar --lucky.word=Argument &

# gradle bootRun --args="--lucky.word=Argument" &
