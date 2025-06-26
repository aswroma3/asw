# Ambienti di esecuzione per VMware Workstation basati su CentOS (sperimentale) 

Questa sezione del repository contiene il codice (*infrastructure-as-code*) di alcuni *ambienti di esecuzione* distribuiti virtuali, 
basati su [VMware Workstation](https://www.vmware.com/products/desktop-hypervisor/workstation-and-fusion) insieme a [Vagrant](https://www.vagrantup.com/). 
Ogni sottosezione (sottocartella) è relativa a un diverso ambiente di esecuzione. 
Le macchine virtuali di questi ambienti di esecuzione sono basate su [CentOS Stream](https://www.centos.org/centos-stream/). 

Questi ambienti sperimentali sono una variante degli analoghi [ambienti per VMware Workstation basati su Ubuntu](../vmware-ubuntu-environments/).

E' importante notare che, in questo repository, il supporto fornito a questi ambienti di esecuzione sperimentali basati su CentOS 
è minore a quello fornito agli ambienti basati su Ubuntu. 
Quindi alcuni di questi ambienti basati su CentOS potrebbero essere non aggiornati oppure obsoleti. 


## Preparazione 

Per usare delle versioni più recenti del software di sviluppo (come *JDK* e *Gradle*) 
potrebbe essere necessario modificare le prime righe dei relativi script di installazione 
(ad esempio, **asw/resources/centos-resources/java/setup-open-jdk.sh** per *Open JDK*), 
indicando il numero della versione da utilizzare. 


## Utilizzo degli ambienti di esecuzione 

Ogni ambiente di esecuzione è composto da uno o più macchine virtuali, 
collegate in una rete privata. 

Ogni ambiente è rappresentato da una diversa cartella di questa sezione del repository. 
Si veda il file **README.md** di una cartella per la descrizione del relativo ambiente. 

Gli ambienti vengono creati con **Vagrant**, 
e possono essere tutti gestiti allo stesso modo. 

Per gestire un ambiente bisogna: 

1. usare una shell (per esempio, Git) del proprio PC 

2. posizionarsi nella cartella dell'ambiente di interesse (ad esempio, **asw/environments/vmware-centos-environments/developer**)

3. per avviare o creare l'ambiente di esecuzione, usare il comando `vagrant up` 

4. per collegarsi con SSH a una macchina virtuale *VM* dell'ambiente, usare il comando `vagrant ssh VM`
    
E' anche possibile: 

* arrestare l'ambiente di esecuzione, con il comando `vagrant halt`

* distruggere l'ambiente di esecuzione, con il comando `vagrant destroy -f`  


## Ambienti 

* [developer](developer/):
  una variante sperimentale basata su CentOS dell'analogo [ambiente developer per VMware Workstation basato su Ubuntu] (../vmware-ubuntu-environments/developer/). 
  
* [kube-cluster](kube-cluster/):
  una variante sperimentale basata su CentOS dell'analogo [ambiente kube-cluster per VMware Workstation basato su Ubuntu] (../vmware-ubuntu-environments/kube-cluster/). 
