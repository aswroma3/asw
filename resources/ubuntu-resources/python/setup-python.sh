#!/bin/bash

echo "================="
echo "installing python"
echo "================="

#apt-get update 
apt-get install -y python3
apt-get install -y python3-venv python3-pip

# evita le cartelle __pycache__ mischiate al codice 
echo export PYTHONPYCACHEPREFIX="~/.cache/pycache" >> /etc/profile.d/pycache.sh
