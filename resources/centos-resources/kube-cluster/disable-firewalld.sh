#!/bin/bash

echo "==================="
echo "disabling firewalld"
echo "==================="

# ci sono problemi di comunicazione tra i nodi del cluster 
# probabilmente la colpa è di firewalld 

# la soluzione drastica è disabilitare permanentemente firewalld 
# prima non ce n'era bisogno, non capisco il problema 
# attenzione, non fare in produzione! 

# comunque anche il firewall di ubuntu (ufw) è normalmente disabilitato
# e questo giustifica questa scelta 

systemctl stop firewalld
systemctl disable firewalld

# un'alternativa (che però non funziona) è aprire tutte le porte che servono 
# ad esempio, 6443 e 10250 per kubeadm e 111 e 2049 per nfs) 
# ho provato, ma non funziona  

#firewall-cmd --permanent --add-port=6443/tcp
#firewall-cmd --permanent --add-port=10250/tcp
#firewall-cmd --permanent --add-port=2379-2380/tcp
#firewall-cmd --permanent --add-port=10251/tcp
#firewall-cmd --permanent --add-port=10252/tcp
#firewall-cmd --permanent --add-port=10255/tcp
#firewall-cmd --permanent --add-port=8472/udp
# NodePorts 
#firewall-cmd --permanent --add-port=30000-32767/tcp
# NFS 
#firewall-cmd --permanent --add-port=111/tcp
#firewall-cmd --permanent --add-port=111/udp
#firewall-cmd --permanent --add-port=2049/tcp
#firewall-cmd --permanent --add-port=2049/udp

#firewall-cmd --add-masquerade --permanent

#systemctl restart firewalld
