#!/bin/bash

# trova tutti gli album

echo "# trova tutti gli album" 
echo $(curl -s localhost:8080/album/album) | jq
echo 

