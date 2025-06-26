#!/bin/bash

echo "======================================"
echo "installing python middleware libraries"
echo "======================================"

# web client libraries 
apt-get install -y python3-httpx python3-requests

# web server libraries 
apt-get install -y python3-fastapi uvicorn python3-uvicorn

# data modeling 
apt-get install -y python3-pydantic

# kafka libraries 
apt-get install -y python3-confluent-kafka

# gRPC libraries 
apt-get install -y python3-grpcio python3-grpc-tools

