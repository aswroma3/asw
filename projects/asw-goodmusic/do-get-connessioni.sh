#!/bin/bash

# trova tutte le connessioni 

echo "# tutte le connessioni" 
echo $(curl -s localhost:8080/connessioni/connessioni)
echo 
