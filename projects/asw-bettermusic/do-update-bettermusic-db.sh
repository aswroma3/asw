#!/bin/bash

# aggiorna i db 

echo Updating BETTERMUSIC databases 

source do-update-albums.sh 
source do-update-recensioni.sh
source do-update-connessioni.sh
 
