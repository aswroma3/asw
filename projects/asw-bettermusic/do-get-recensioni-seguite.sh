#!/bin/bash

# trova tutte le recensioni seguite degli utenti noti 

for UTENTE in Alice Bob Carlo Dario Elisa Francesca; do 
	echo "# le recensioni seguite da $UTENTE" 
	echo $(curl -s localhost:8080/recensioni-seguite/recensioniseguite/$UTENTE) | jq
	echo 
done 
