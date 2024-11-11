#!/bin/bash

# Script per avviare l'applicazione GoodMusic

echo Running GOODMUSIC 

docker run -d --hostname localhost --name asw-consul --publish 8500:8500 docker.io/hashicorp/consul

sleep 4 

java -Xms64m -Xmx128m -jar recensioni/build/libs/recensioni.jar &
java -Xms64m -Xmx128m -jar connessioni/build/libs/connessioni.jar &
java -Xms64m -Xmx128m -jar recensioni-seguite/build/libs/recensioni-seguite.jar &

java -Xms64m -Xmx128m -jar api-gateway/build/libs/api-gateway.jar &
