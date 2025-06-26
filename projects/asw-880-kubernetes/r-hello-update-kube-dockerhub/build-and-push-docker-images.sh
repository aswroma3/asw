#!/bin/bash

# per creare le immagini docker dell'applicazione e salvarle su docker hub 

# prerequisito: aver eseguito il docker login su docker hub 

docker compose build

docker compose push
