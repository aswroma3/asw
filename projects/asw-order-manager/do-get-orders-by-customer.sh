#!/bin/bash

# trova tutti gli ordini di un cliente

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: customerName"
	exit 1 
fi

CUSTOMER=""

for i in "$@"; do
	if [[ $CUSTOMER = "" ]]
	then 
		CUSTOMER="$i"; 
	else 
#		CUSTOMER="$PRODUCTNAME $i"; 
		CUSTOMER="$PRODUCTNAME%20$i"; 
	fi
done;

echo "# trova tutti gli ordini di un cliente" 
echo $(curl -s localhost:8080/orderservice/findorders/customer/${CUSTOMER}) | json_pp
echo 

