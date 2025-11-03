#!/bin/bash

# inizializza il db degli album

curl -X POST "http://localhost:8080/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"The Dark Side of the Moon\", \"artista\": \"Pink Floyd\", 
           \"generi\": [ \"Rock\", \"Progressive Rock\" ] }"
echo 

curl -X POST "http://localhost:8080/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"The Wall\", \"artista\": \"Pink Floyd\", 
           \"generi\": [ \"Progressive Rock\" ] }"
echo 

curl -X POST "http://localhost:8080/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Selling England by the Pound\", \"artista\": \"Genesis\", 
           \"generi\": [ \"Progressive Rock\" ] }"
echo 

curl -X POST "http://localhost:8080/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Abbey Road\", \"artista\": \"The Beatles\", 
           \"generi\": [ \"Rock\", \"Pop\" ] }"
echo 

curl -X POST "http://localhost:8080/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Thriller\", \"artista\": \"Michael Jackson\", 
           \"generi\": [ \"Pop\", \"Contemporary R&B\" ] }"
echo 

curl -X POST "http://localhost:8080/album/album" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"titolo\": \"Kind of Blue\", \"artista\": \"Miles Davis\", 
           \"generi\": [ \"Jazz\" ] }"
echo 
