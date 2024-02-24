#!/bin/bash

# uno dei prodotti non esiste 

curl -X POST "http://localhost:8080/orderservice/orders" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"customer\": \"Woody\", \"address\": \"Roma\", 
	       \"orderItems\": [ { \"product\": \"Pace e Guerra\", \"quantity\": \"1\" }, { \"product\": \"Anna Karenina\", \"quantity\": \"10\" } ], 
		   \"total\": \"50.97\" }"
echo 

# non deve dare errore, ma non deve passare la validazione 

echo "# convalida l'ordine 7" 
echo $(curl -s localhost:8080/ordervalidationservice/ordervalidations/7) | json_pp
echo 

# non deve dare errore, ma non deve passare la validazione (l'ordine non esiste) 

echo "# convalida l'ordine 99" 
echo $(curl -s localhost:8080/ordervalidationservice/ordervalidations/99) | json_pp
echo 