#!/bin/bash

echo "========================================"
echo "preparing python asw virtual environment"
echo "========================================"

ASW_VENV=/home/asw/venv

python3 -m venv $ASW_VENV

/home/asw/venv/bin/python3 -m pip install --upgrade pip

source $ASW_VENV/bin/activate 

# web client libraries 
pip install httpx requests 

# web server libraries 
pip install fastapi "uvicorn[standard]" "fastapi[standard]" 

# data modeling 
pip install pydantic

# kafka libraries 
pip install confluent-kafka

# gRPC libraries 
pip install grpcio grpcio-tools

# dependency injector 
pip install dependency-injector
