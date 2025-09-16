#!/bin/bash

# numero di chiamate 
N=${1:-10}

# intervallo tra una chiamata e l'altra (ms) 
DELAY=${2:-500}

source /home/asw/venv/bin/activate 

python3 -m rest-python-client $N $DELAY

