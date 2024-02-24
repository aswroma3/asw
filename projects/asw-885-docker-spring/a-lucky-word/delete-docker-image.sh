#!/bin/bash

DOCKERHUB_USERNAME=aswroma3 
IMAGE_NAME=lucky-word

docker image rm ${DOCKERHUB_USERNAME}/${IMAGE_NAME}
