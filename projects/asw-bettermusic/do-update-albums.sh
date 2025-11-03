#!/bin/bash

# aggiunge alcuni album

curl -X POST "http://localhost:8080/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Born to Run\", \"artista\": \"Bruce Springsteen\", 
           \"generi\": [ \"Rock\" ] }"
echo 

curl -X POST "http://localhost:8080/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Sgt. Pepper's Lonely Hearts Club Band\", \"artista\": \"The Beatles\", 
           \"generi\": [ \"Rock\", \"Pop\" ] }"
echo 

