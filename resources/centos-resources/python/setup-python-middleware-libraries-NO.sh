#!/bin/bash

echo "======================================"
echo "installing python middleware libraries"
echo "======================================"

# pip install eseguito come root genera dei warning (che possono essere ignorati) 

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

# in alternativa, per evitare i warning, installa solo per l'utente vagrant  
#sudo --user=vagrant pip install XXX 
