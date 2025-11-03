#!/bin/bash

# inizializza tutti i db 

echo Initializing BETTERMUSIC databases 

source do-init-albums.sh 
source do-init-recensioni.sh
source do-init-connessioni.sh
 
