#!/bin/bash

# inizializza il db delle connessioni 

# Alice segue solo artisti 
# Bob segue solo recensori 
# Carlo segue solo generi 
# Dario segue sia artisti che recensori 
# Elisa segue sia recensori che generi 
# Francesca segue sia artisti che generi 

# connessioni con artisti 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Alice\", \"seguito\": \"Pink Floyd\", \"ruolo\": \"ARTISTA\"}"
echo 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Alice\", \"seguito\": \"The Beatles\", \"ruolo\": \"ARTISTA\"}"
echo 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Dario\", \"seguito\": \"The Beatles\", \"ruolo\": \"ARTISTA\"}"
echo 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Dario\", \"seguito\": \"Michael Jackson\", \"ruolo\": \"ARTISTA\"}"
echo 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Francesca\", \"seguito\": \"Genesis\", \"ruolo\": \"ARTISTA\"}"
echo 

# connessioni con recensori 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Bob\", \"seguito\": \"Woody\", \"ruolo\": \"RECENSORE\"}"
echo 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Bob\", \"seguito\": \"OndaRock\", \"ruolo\": \"RECENSORE\"}"
echo 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Dario\", \"seguito\": \"OndaRock\", \"ruolo\": \"RECENSORE\"}"
echo 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Elisa\", \"seguito\": \"Woody\", \"ruolo\": \"RECENSORE\"}"
echo 

# connessioni con generi 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Carlo\", \"seguito\": \"Rock\", \"ruolo\": \"GENERE\"}"
echo 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Carlo\", \"seguito\": \"Pop\", \"ruolo\": \"GENERE\"}"
echo 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Elisa\", \"seguito\": \"Pop\", \"ruolo\": \"GENERE\"}"
echo 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Elisa\", \"seguito\": \"Jazz\", \"ruolo\": \"GENERE\"}"
echo 

curl -X POST "http://localhost:8080/connessioni/connessioni" -H "accept: */*" -H "Content-Type: application/json" \
     -d "{ \"utente\": \"Francesca\", \"seguito\": \"Progressive Rock\", \"ruolo\": \"GENERE\"}"
echo 
