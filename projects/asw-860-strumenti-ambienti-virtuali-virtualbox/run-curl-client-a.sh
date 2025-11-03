#!/bin/bash

# accede al servizio sulla VM a
echo 
echo "GET localhost:8081 -->" $(curl -s --get "http://localhost:8081") 
echo 
