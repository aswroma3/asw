#!/bin/bash

# inizializza il db dei prodotti 

curl -X POST "http://localhost:8080/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"Guerra e Pace\", \"category\": \"Libro\", \"stockLevel\": \"3\", \"price\": \"19.99\" }"
echo 

curl -X POST "http://localhost:8080/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"Anna Karenina\", \"category\": \"Libro\", \"stockLevel\": \"1\", \"price\": \"10.99\" }"
echo 

curl -X POST "http://localhost:8080/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"I promessi sposi\", \"category\": \"Libro\", \"stockLevel\": \"3\", \"price\": \"9.99\" }"
echo 

curl -X POST "http://localhost:8080/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"Il Signore degli Anelli\", \"category\": \"Libro\", \"stockLevel\": \"3\", \"price\": \"29.99\" }"
echo 

curl -X POST "http://localhost:8080/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"1984\", \"category\": \"Libro\", \"stockLevel\": \"5\", \"price\": \"8.99\" }"
echo 

curl -X POST "http://localhost:8080/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"The Dark Side Of The Moon\", \"category\": \"Musica\", \"stockLevel\": \"4\", \"price\": \"19.99\" }"
echo 