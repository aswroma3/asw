#!/bin/bash

# per salvare le immagini docker dell'applicazione su docker hub 

# per creare le immagini docker dell'applicazione 
# prerequisito: build java
#               build docker 
#               docker login 

# docker compose push
docker buildx bake --push