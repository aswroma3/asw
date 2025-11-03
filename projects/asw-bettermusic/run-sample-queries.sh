#!/bin/bash

# esegue un insieme di interrogazioni di esempio 

echo "# tutti gli album"  
echo $(curl -s localhost:8080/album/album) | jq .
echo 

echo "# l'album 1"  
echo $(curl -s localhost:8080/album/album/1) | jq .
echo 

echo "# l'album The Wall dei Pink Floyd"  
echo $(curl -s "localhost:8080/album/cercaalbum?titolo=The%20Wall&artista=Pink%20Floyd") | jq .
echo 

echo "# tutti gli album dei Pink Floyd"  
echo $(curl -s "localhost:8080/album/cercaalbum/artista/Pink%20Floyd") | jq .
echo 

echo "# tutti gli album di genere Progressive Rock"  
echo $(curl -s "localhost:8080/album/cercaalbum/genere/Progressive%20Rock") | jq .
echo 

echo "# tutte le recensioni"  
echo $(curl -s localhost:8080/recensioni/recensioni) | jq .
echo 

echo "# la recensione 1" 
echo $(curl -s localhost:8080/recensioni/recensioni/1) | jq .
echo 

echo "# tutte le recensioni del recensore Woody" 
echo $(curl -s localhost:8080/recensioni/cercarecensioni/recensore/Woody) | jq .
echo 

echo "# tutte le recensioni dell'album 1" 
echo $(curl -s localhost:8080/recensioni/cercarecensioni/album/1) | jq .
echo 

echo "# tutte le connessioni" 
echo $(curl -s localhost:8080/connessioni/connessioni) | jq .
echo 

echo "# le connessioni di Alice" 
echo $(curl -s localhost:8080/connessioni/connessioni/Alice) | jq .
echo 

echo "# le connessioni di Bob" 
echo $(curl -s localhost:8080/connessioni/connessioni/Bob) | jq .
echo 

echo "# le connessioni di Carlo" 
echo $(curl -s localhost:8080/connessioni/connessioni/Carlo) | jq .
echo 

echo "# le recensioni seguite da Alice" 
echo $(curl -s localhost:8080/recensioni-seguite/recensioniseguite/Alice) | jq .
echo 

echo "# le recensioni seguite da Bob" 
echo $(curl -s localhost:8080/recensioni-seguite/recensioniseguite/Bob) | jq .
echo 

echo "# le recensioni seguite da Carlo" 
echo $(curl -s localhost:8080/recensioni-seguite/recensioniseguite/Carlo) | jq .
echo 

echo "# conta le recensioni seguite da Alice" 
echo $(curl -s localhost:8080/recensioni-seguite/recensioniseguite/Alice) | jq length 
echo 

echo "# conta le recensioni seguite da Bob" 
echo $(curl -s localhost:8080/recensioni-seguite/recensioniseguite/Bob) | jq length 
echo 

echo "# conta le recensioni seguite da Carlo" 
echo $(curl -s localhost:8080/recensioni-seguite/recensioniseguite/Carlo) | jq length 
echo 
