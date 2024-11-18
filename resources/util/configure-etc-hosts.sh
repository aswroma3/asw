#!/bin/bash

HOSTS_FILE=/etc/hosts 

MY_HOSTNAME=$1

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
# - "10.11.1.121 nome-dell-host-indicato-come-parametro"
function configureWorkstationEtcHosts {
	# calcola il mio indirizzo IP (sulla rete 10.11.1.xx)
	# ubuntu 24.04 
	MY_IP_ADDR=$(ip address | grep 10.11.1. | awk '{ print $2 }' | cut -d/ -f1)
	echo "adding entries for" ${MY_HOSTNAME} "to /etc/hosts on" ${MY_IP_ADDR}
	echo ${MY_IP_ADDR}" "${MY_HOSTNAME} >> ${HOSTS_FILE}
}

echo "===================="
echo "configure /etc/hosts"
echo "===================="
createModifiedEtcHosts
configureWorkstationEtcHosts