#!/bin/bash

# inizializza il db delle recensioni 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"album\": \"The Dark Side of the Moon\", \"artista\": \"Pink Floyd\", \"genere\": \"Rock\", 
	       \"sunto\": \"Il lato buio dell'animo umano\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"album\": \"The Wall\", \"artista\": \"Pink Floyd\", \"genere\": \"Rock\", 
	       \"sunto\": \"Le ossessioni di Pink\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"album\": \"Abbey Road\", \"artista\": \"The Beatles\", \"genere\": \"Pop\", 
	       \"sunto\": \"Una collezione di canzoni superbe\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"album\": \"The Dark Side of the Moon\", \"artista\": \"Pink Floyd\", \"genere\": \"Rock\", 
	       \"sunto\": \"Leggendario!\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"album\": \"Abbey Road\", \"artista\": \"The Beatles\", \"genere\": \"Pop\", 
	       \"sunto\": \"L'album dei Beatles più amato\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"album\": \"Thriller\", \"artista\": \"Michael Jackson\", \"genere\": \"Pop\", 
	       \"sunto\": \"The King of Pop!\", \"testo\": \"Bla bla bla...\" }"
echo 