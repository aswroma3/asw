#!/bin/bash

# convalida alcuni ordini 

echo "# convalida l'ordine 1" 
echo $(curl -s localhost:8080/ordervalidationservice/ordervalidations/1) | json_pp
echo 

echo "# convalida l'ordine 2" 
echo $(curl -s localhost:8080/ordervalidationservice/ordervalidations/2) | json_pp
echo 

echo "# convalida l'ordine 3" 
echo $(curl -s localhost:8080/ordervalidationservice/ordervalidations/3) | json_pp
echo 

