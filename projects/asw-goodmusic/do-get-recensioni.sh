#!/bin/bash

# trova tutte le recensioni 

echo "# trova tutte le recensioni" 
echo $(curl -s localhost:8080/recensioni/recensioni)
echo 

