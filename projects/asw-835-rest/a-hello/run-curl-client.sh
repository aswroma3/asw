#!/bin/bash

# accede al servizio Hello
echo 
echo "GET localhost:8080/hello/World"
echo $(curl -s --get "http://localhost:8080/hello/World") 
