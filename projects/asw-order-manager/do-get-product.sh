#!/bin/bash

# trova un prodotto 

# esempio: ./do-get-product.sh Il Signore degli Anelli

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

echo "# trova il prodotto $PRODUCTNAME" 
echo $(curl -s localhost:8080/productservice/products/${PRODUCTNAME}) # | json_pp
echo 

