#!/bin/bash

# trova una recensione 

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: id-recensione"
	exit 1 
fi

RECENSIONE=$1 

echo "# trova la recensione $RECENSIONE" 
echo $(curl -s localhost:8080/recensioni/recensioni/${RECENSIONE})
echo 

