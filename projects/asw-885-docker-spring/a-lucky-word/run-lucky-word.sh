#!/bin/bash

# esegue l'applicazione contenitorizzata  
# prerequisito: (1) build del progetto Java
# prerequisito: (2) crea l'immagine docker

docker container run -d -p 8080:8080 --name=lucky-word lucky-word
# docker container run -d -p 8080:8080 --name=lucky-word -e "SPRING_PROFILES_ACTIVE=english" lucky-word 
# docker container run -d -p 8080:8080 --name=lucky-word -e "SPRING_PROFILES_ACTIVE=italian" lucky-word 


