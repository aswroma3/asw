#!/bin/bash

# aggiunge alcune recensioni 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"Woody\", \"album\": \"Born to Run\", \"artista\": \"Bruce Springsteen\", \"genere\": \"Rock\", 
	       \"sunto\": \"Un capolavoro!\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"OndaRock\", \"album\": \"Sgt. Pepper's Lonely Hearts Club Band\", \"artista\": \"The Beatles\", \"genere\": \"Pop\", 
	       \"sunto\": \"Con questo album, la musica pop cambia definitivamente aspetto e contenuti\", \"testo\": \"Bla bla bla...\" }"
echo 

curl -X POST "http://localhost:8080/recensioni/recensioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"recensore\": \"RollingStone\", \"album\": \"Kind of Blue\", \"artista\": \"Miles Davis\", \"genere\": \"Jazz\", 
	       \"sunto\": \"Uno degli album più importanti, influenti e popolari del Jazz\", \"testo\": \"Bla bla bla...\" }"
echo 