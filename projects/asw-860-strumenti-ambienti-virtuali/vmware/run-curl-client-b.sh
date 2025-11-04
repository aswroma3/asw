#!/bin/bash

# accede al servizio sulla VM b
echo 
echo "GET localhost:8082 -->" $(curl -s --get "http://localhost:8082") 
echo 
