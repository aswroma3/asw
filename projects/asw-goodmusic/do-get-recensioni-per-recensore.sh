#!/bin/bash

# trova tutte le recensioni di un certo recensore  

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: recensore"
	exit 1 
fi

# se un recensore contiene spazi deve essere racchiuso tra virgolette 
RECENSORE=$(echo $1 | sed -e "s/ /%20/g") 

echo "# tutte le recensioni del recensore $1" 
echo $(curl -s localhost:8080/recensioni/cercarecensioni/recensore/$RECENSORE)
echo 
