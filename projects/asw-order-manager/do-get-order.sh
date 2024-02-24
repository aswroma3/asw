#!/bin/bash

# trova un ordine 

if [ $# -eq 0 ]
  then
    echo "Manca il parametro: orderId"
	exit 1 
fi

ORDERID=$1 

echo "# trova l'ordine $ORDERID" 
echo $(curl -s localhost:8080/orderservice/orders/${ORDERID}) | json_pp
echo 

