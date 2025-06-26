#!/bin/bash

docker build --rm -t sentence-sentence ./sentence-service
docker build --rm -t sentence-sentence-async ./sentence-service-async
docker build --rm -t sentence-word ./word-service 
docker build --rm -t sentence-apigateway ./api-gateway
