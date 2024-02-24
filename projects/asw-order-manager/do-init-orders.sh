#!/bin/bash

# inizializza il db degli ordini 

curl -X POST "http://localhost:8080/orderservice/orders" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"customer\": \"Woody\", \"address\": \"Milano\", 
	       \"orderItems\": [ { \"product\": \"I promessi sposi\", \"quantity\": \"2\" } ], 
	       \"total\": \"19.98\" }"
echo 

curl -X POST "http://localhost:8080/orderservice/orders" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"customer\": \"Woody\", \"address\": \"Roma\", 
	       \"orderItems\": [ { \"product\": \"Guerra e Pace\", \"quantity\": \"2\" }, { \"product\": \"Anna Karenina\", \"quantity\": \"2\" } ], 
		   \"total\": \"50.97\" }"
echo 

curl -X POST "http://localhost:8080/orderservice/orders" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"customer\": \"Robert\", \"address\": \"Roma\", 
	       \"orderItems\": [ { \"product\": \"Il Signore degli Anelli\", \"quantity\": \"1\" } ], 
	       \"total\": \"19.99\" }"
echo 

curl -X POST "http://localhost:8080/orderservice/orders" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"customer\": \"Woody\", \"address\": \"Milano\", 
	       \"orderItems\": [ { \"product\": \"Il Signore degli Anelli\", \"quantity\": \"1\" }, { \"product\": \"1984\", \"quantity\": \"1\" } ], 
	       \"total\": \"38.98\" }"
echo 

curl -X POST "http://localhost:8080/orderservice/orders" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"customer\": \"Mary\", \"address\": \"Firenze\", 
	       \"orderItems\": [ { \"product\": \"Il Signore degli Anelli\", \"quantity\": \"1\" }, { \"product\": \"The Dark Side Of The Moon\", \"quantity\": \"1\" } ], 
	       \"total\": \"49.98\" }"
echo 

curl -X POST "http://localhost:8080/orderservice/orders" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"customer\": \"Robert\", \"address\": \"Roma\", 
	       \"orderItems\": [ { \"product\": \"Anna Karenina\", \"quantity\": \"3\" } ], 
	       \"total\": \"32.97\" }"
echo 

