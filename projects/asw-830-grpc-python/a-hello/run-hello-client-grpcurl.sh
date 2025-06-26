#!/bin/bash

PROTO_FOLDER=hello_proto

grpcurl -d '{"name":"Luca"}' -plaintext -proto ${PROTO_FOLDER}/HelloService.proto localhost:50051 HelloService/sayHello
