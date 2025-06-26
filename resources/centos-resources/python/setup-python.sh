#!/bin/bash

echo "================="
echo "installing python"
echo "================="

yum -y install python3-devel python3-pip

# evita le cartelle __pycache__ mischiate al codice 
echo export PYTHONPYCACHEPREFIX="~/.cache/pycache" >> /etc/profile.d/pycache.sh
