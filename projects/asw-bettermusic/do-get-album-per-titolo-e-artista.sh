#!/bin/bash

# trova un album dato titolo e artista 

if [ $# -eq 0 ]
  then
    echo "Mancano i parametri: titolo artista"
	exit 1 
fi

# se titolo o artista contiene spazi deve essere racchiuso tra virgolette 
TITOLO=$(echo $1 | sed -e "s/ /%20/g") 
ARTISTA=$(echo $2 | sed -e "s/ /%20/g") 

echo "# l'album con titolo $1 e artista $2" 
#echo "localhost:8080/album/cercaalbum?titolo=$TITOLO&artista=$ARTISTA"
echo $(curl -s "localhost:8080/album/cercaalbum?titolo=$TITOLO&artista=$ARTISTA") | jq
echo 

