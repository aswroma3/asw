#!/bin/bash

# aggiunge alcune recensioni 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"titoloAlbum\": \"Born to Run\", \"artistaAlbum\": \"Bruce Springsteen\", 
	       \"sunto\": \"Un capolavoro!\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"titoloAlbum\": \"Sgt. Pepper's Lonely Hearts Club Band\", \"artistaAlbum\": \"The Beatles\", 
	       \"sunto\": \"Con questo album, la musica pop cambia definitivamente aspetto e contenuti\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"RollingStone\", \"titoloAlbum\": \"Kind of Blue\", \"artistaAlbum\": \"Miles Davis\", 
	       \"sunto\": \"Uno degli album più importanti, influenti e popolari del Jazz\", \"testo\": \"Bla bla bla...\" }"
echo 