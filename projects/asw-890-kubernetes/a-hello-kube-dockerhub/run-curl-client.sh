#!/bin/bash

# accede al servizio eseguito localmente 

# itera la richiesta per N volte

N=${1:-10}
for ((i=0; i<$N; i++)); do 
	curl localhost:8080
	echo "" ; 
done 
