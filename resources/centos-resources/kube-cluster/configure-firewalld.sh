#!/bin/bash

echo "====================="
echo "configuring firewalld"
echo "====================="

# ci sono problemi di comunicazione tra i nodi del cluster 
# probabilmente la colpa è di firewalld 

# una soluzione drastica è disabilitare permanentemente firewalld 
# prima non ce n'era bisogno, non capisco il problema 
# attenzione, non fare in produzione! 
# comunque il firewall di ubuntu (ufw) è normalmente disabilitato

systemctl stop firewalld
systemctl disable firewalld

# oppure proviamo ad aprire le porte 6443 e 10250
# ho provato, ma non funziona (per esempio, fallisce con nfs) 

#firewall-cmd --permanent --add-port=6443/tcp
#firewall-cmd --permanent --add-port=10250/tcp
#firewall-cmd --permanent --add-port=2379-2380/tcp
#firewall-cmd --permanent --add-port=10251/tcp
#firewall-cmd --permanent --add-port=10252/tcp
#firewall-cmd --permanent --add-port=10255/tcp
#firewall-cmd --permanent --add-port=8472/udp
#firewall-cmd --add-masquerade --permanent

# only if you want NodePorts exposed on control plane IP as well
#firewall-cmd --permanent --add-port=30000-32767/tcp

#systemctl restart firewalld
