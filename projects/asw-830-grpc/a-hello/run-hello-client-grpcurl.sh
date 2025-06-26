#!/bin/bash

PROTO_FOLDER=hello-service-api/src/main/proto

grpcurl -d '{"name":"Luca"}' -plaintext -proto ${PROTO_FOLDER}/HelloService.proto localhost:50051 HelloService/sayHello
