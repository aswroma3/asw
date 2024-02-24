#!/bin/bash

HOSTS_FILE=/etc/hosts 

# aggiunge un # all'inizio delle linee che iniziano con 127.0.0.1 e 127.0.1.1 
function createModifiedEtcHosts
{
	echo "modifying 127.0.x.x entries in /etc/hosts"
	# Legge il file $INFILE e lo copia in $OUTFILE, ma: 
	# - aggiunge un # all'inizio delle linee che iniziano con 127.0. 
	sed s/^'127.0.'/'# 127.0.'/ ${HOSTS_FILE} > ${HOSTS_FILE}.new
	# aggiunge di nuovo 127.0.0.1 localhost
    echo "127.0.0.1 localhost" >> ${HOSTS_FILE}.new 
	mv ${HOSTS_FILE} ${HOSTS_FILE}.bak
	mv ${HOSTS_FILE}.new ${HOSTS_FILE}
}

# aggiunge a /etc/hosts le seguenti entry 
# - "10.11.1.121 workstation"
function configureWorkstationEtcHosts {
	# calcola il mio indirizzo IP (sulla rete 10.11.1.xx)
	# ubuntu 16.04 
	# MY_IP_ADDR=$(ifconfig  | grep 'inet addr:'| grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $1}' | grep '10.11.1.')
	# ubuntu 18.04 
	# MY_IP_ADDR=$(ifconfig  | grep 'inet' | grep -v 'inet6' | grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $2}' | grep '10.11.1.')
	# ubuntu 20.04 
	MY_IP_ADDR=$(ip address | grep 10.11.1. | awk '{ print $2 }' | cut -d/ -f1)
	echo "adding entries for workstation to /etc/hosts on " ${MY_IP_ADDR}
	echo ${MY_IP_ADDR} " workstation" >> ${HOSTS_FILE}
}

echo "configure /etc/hosts on workstation node"
createModifiedEtcHosts
configureWorkstationEtcHosts