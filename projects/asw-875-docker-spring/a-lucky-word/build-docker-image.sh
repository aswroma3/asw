#!/bin/bash

# (2) crea l'immagine docker  
# prerequisito: (1) build del progetto Java

docker image build --rm -t lucky-word . 
