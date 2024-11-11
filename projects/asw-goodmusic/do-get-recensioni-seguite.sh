#!/bin/bash

# trova tutte le recensioni seguite da un certo utente 

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: utente"
	exit 1 
fi

# se l'utente contiene spazi deve essere racchiuso tra virgolette 
UTENTE=$(echo $1 | sed -e "s/ /%20/g") 

echo "# le recensioni seguite da $1" 
echo $(curl -s localhost:8080/recensioni-seguite/recensioniseguite/$UTENTE)
echo 
