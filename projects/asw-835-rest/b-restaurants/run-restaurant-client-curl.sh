#!/bin/bash

echo "GET localhost:8080/rest/restaurants -->" $(curl -s --get "http://localhost:8080/rest/restaurants") 

echo "" 

echo "GET localhost:8080/rest/restaurants/1 -->" $(curl -s --get "http://localhost:8080/rest/restaurants/1") 

echo "" 

