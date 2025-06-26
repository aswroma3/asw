#!/bin/bash

# per creare le immagini docker dell'applicazione 
# prerequisito: build java

# docker compose build
docker buildx bake

