#!/bin/bash

# trova tutti gli ordini per un prodotto

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: productName"
	exit 1 
fi

PRODUCTNAME=""

for i in "$@"; do
	if [[ $PRODUCTNAME = "" ]]
	then 
		PRODUCTNAME="$i"; 
	else 
#		PRODUCTNAME="$PRODUCTNAME $i"; 
		PRODUCTNAME="$PRODUCTNAME%20$i"; 
	fi
done;

echo "# trova tutti gli ordini per un prodotto" 
echo $(curl -s localhost:8080/orderservice/findorders/product/${PRODUCTNAME}) | json_pp
echo 

