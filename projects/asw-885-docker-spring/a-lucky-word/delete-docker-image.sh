#!/bin/bash

DOCKERHUB_USERNAME=aswroma3 

docker image rm lucky-word
docker image rm ${DOCKERHUB_USERNAME}/lucky-word
