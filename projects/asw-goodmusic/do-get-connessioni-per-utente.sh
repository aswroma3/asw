#!/bin/bash

# trova tutte le connessioni per un certo utente  

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: utente"
	exit 1 
fi

# se un utente contiene spazi deve essere racchiuso tra virgolette 
UTENTE=$(echo $1 | sed -e "s/ /%20/g") 

echo "# tutte le connessioni per l'utente $1" 
echo $(curl -s localhost:8080/connessioni/connessioni/$UTENTE)
echo 

