#!/bin/bash

source /home/asw/venv/bin/activate 

python3 -m hello_service.main &

# l'host di default è (solo) 127.0.0.1 - con 0.0.0.0 è visibile a tutti 
# la porta di default è 8000
# reload ricarica automaticamente il codice se viene modificato 
#uvicorn hello_service.main:app --host 0.0.0.0 --port 8080 --reload & 
# vedi anche localhost:8080/docs per la documentazione delle API

