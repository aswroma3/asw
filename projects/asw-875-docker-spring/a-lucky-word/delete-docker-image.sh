#!/bin/bash

DOCKERHUB_USERNAME=aswroma3 
IMAGE_NAME=lucky-word
VERSION=2025-09

docker image rm ${IMAGE_NAME}
docker image rm ${DOCKERHUB_USERNAME}/${IMAGE_NAME}
docker image rm ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${VERSION}
docker image rm ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:latest
