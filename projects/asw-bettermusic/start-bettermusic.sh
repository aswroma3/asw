#!/bin/bash

# Script per avviare l'applicazione BetterMusic

echo Starting BETTERMUSIC

java -Xms64m -Xmx128m -jar album/build/libs/album.jar &
java -Xms64m -Xmx128m -jar recensioni/build/libs/recensioni.jar &
java -Xms64m -Xmx128m -jar connessioni/build/libs/connessioni.jar &
java -Xms64m -Xmx128m -jar recensioni-seguite/build/libs/recensioni-seguite.jar &
# oppure: 
# ASW_RECENSIONISEGUITESERVICE_IMPLEMENTATION=RecensioniSeguiteServiceRestBasedImpl java -Xms64m -Xmx128m -jar recensioni-seguite/build/libs/recensioni-seguite.jar &

java -Xms64m -Xmx128m -jar api-gateway/build/libs/api-gateway.jar &
