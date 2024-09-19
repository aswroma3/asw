#!/bin/bash

echo "================================"
echo "installing python rest libraries"
echo "================================"

# server libraries 
#pip install fastapi
#pip install "uvicorn[standard]"
#pip install pydantic

# client libraries (ubuntu 22.04) 
#pip install httpx 
#pip install requests

# client libraries (ubuntu 24.04) 
apt-get install -y python3-httpx
apt-get install -y python3-pydantic