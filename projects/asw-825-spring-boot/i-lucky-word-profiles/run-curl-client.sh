#!/bin/bash

# accede al servizio 
echo 
echo "GET localhost:8080/lucky-word"
echo $(curl -s --get "http://localhost:8080/lucky-word") 
