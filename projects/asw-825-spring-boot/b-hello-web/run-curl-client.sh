#!/bin/bash

# accede al servizio 
echo 
echo "GET localhost:8080/hello -->" $(curl -s --get "http://localhost:8080/hello") 
