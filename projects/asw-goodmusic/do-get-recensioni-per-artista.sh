#!/bin/bash

# trova tutte le recensioni relative a un certo artista  

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: artista"
	exit 1 
fi

# se un artista contiene spazi deve essere racchiuso tra virgolette 
ARTISTA=$(echo $1 | sed -e "s/ /%20/g") 

echo "# tutte le recensioni per l'artista $1" 
echo $(curl -s localhost:8080/recensioni/cercarecensioni/artista/$ARTISTA)
echo 
