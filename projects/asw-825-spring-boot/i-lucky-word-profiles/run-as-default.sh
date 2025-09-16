#!/bin/bash

# Script per avviare il servizio lucky-word 

echo Running as DEFAULT  

java -jar build/libs/lucky-word.jar &

# gradle bootRun &