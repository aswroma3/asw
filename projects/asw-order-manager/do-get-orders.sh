#!/bin/bash

# trova tutti gli ordini 

echo "# trova tutti gli ordini" 
echo $(curl -s localhost:8080/orderservice/orders) | json_pp
echo 

