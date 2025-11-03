#!/bin/bash

# trova tutte le recensioni relative a un certo album  

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: id-album"
	exit 1 
fi

# se l'id album contiene spazi deve essere racchiuso tra virgolette 
IDALBUM=$(echo $1 | sed -e "s/ /%20/g") 

echo "# tutte le recensioni per l'album con id $1" 
echo $(curl -s localhost:8080/recensioni/cercarecensioni/album/$IDALBUM) | jq
echo 
