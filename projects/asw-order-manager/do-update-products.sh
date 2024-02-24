#!/bin/bash

# aggiorna il db dei prodotti 

curl -X PATCH "http://localhost:8080/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"Guerra e Pace\", \"stockLevelVariation\": \"-1\" }"
echo 

curl -X PATCH "http://localhost:8080/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"Anna Karenina\", \"stockLevelVariation\": \"2\" }"
echo 

curl -X PATCH "http://localhost:8080/productservice/products" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"name\": \"I promessi sposi\", \"stockLevelVariation\": \"-2\" }"
echo 
