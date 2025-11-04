#!/bin/bash

# accede al servizio sulla VM c
echo 
echo "GET localhost:8083 -->" $(curl -s --get "http://localhost:8083") 
echo 
