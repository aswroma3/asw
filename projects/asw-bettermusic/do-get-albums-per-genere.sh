#!/bin/bash

# trova tutti gli album di un genere

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: genere"
	exit 1 
fi

# se un genere contiene spazi deve essere racchiuso tra virgolette 
GENERE=$(echo $1 | sed -e "s/ /%20/g") 

echo "# tutti gli album del genere $1" 
echo $(curl -s localhost:8080/album/cercaalbum/genere/$GENERE) | jq
echo 

