#!/bin/bash

# trova un album dato l'id 

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: id"
	exit 1 
fi

# se un id contiene spazi deve essere racchiuso tra virgolette 
ID=$(echo $1 | sed -e "s/ /%20/g") 

echo "# l'album con id $1" 
echo $(curl -s localhost:8080/album/album/$ID) | jq
echo 

