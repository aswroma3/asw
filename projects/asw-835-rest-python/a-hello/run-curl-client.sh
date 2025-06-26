#!/bin/bash

# accede al servizio Hello
echo 
echo "GET localhost:8080/hello/Luca -->" $(curl -s --get "http://localhost:8080/hello/Luca") 

echo 
echo "GET localhost:8080 -->" $(curl -s --get "http://localhost:8080") 

echo 
echo "GET localhost:8080/hello/ -->" $(curl -s --get "http://localhost:8080/hello/") 

echo 
echo "GET localhost:8080/hello -->" $(curl -s --get "http://localhost:8080/hello") 
