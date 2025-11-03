#!/bin/bash

# inizializza il db delle recensioni 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"titoloAlbum\": \"The Dark Side of the Moon\", \"artistaAlbum\": \"Pink Floyd\", 
	       \"sunto\": \"Il lato buio dell'animo umano\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"titoloAlbum\": \"The Wall\", \"artistaAlbum\": \"Pink Floyd\", 
	       \"sunto\": \"Le ossessioni di Pink\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"titoloAlbum\": \"Abbey Road\", \"artistaAlbum\": \"The Beatles\", 
	       \"sunto\": \"Una collezione di canzoni superbe\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"titoloAlbum\": \"The Dark Side of the Moon\", \"artistaAlbum\": \"Pink Floyd\", 
	       \"sunto\": \"Leggendario!\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"titoloAlbum\": \"Abbey Road\", \"artistaAlbum\": \"The Beatles\", 
	       \"sunto\": \"L'album dei Beatles più amato\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"titoloAlbum\": \"Thriller\", \"artistaAlbum\": \"Michael Jackson\", 
	       \"sunto\": \"The King of Pop!\", \"testo\": \"Bla bla bla...\" }"
echo 