# Ambienti di esecuzione per VMware Workstation basati su Ubuntu

Questa sezione del repository contiene il codice (*infrastructure-as-code*) di alcuni *ambienti di esecuzione* distribuiti virtuali, 
basati su [Vagrant](https://www.vagrantup.com/) insieme a [VMware Workstation](https://www.vmware.com/products/desktop-hypervisor/workstation-and-fusion). 
Ogni sottosezione (sottocartella) è relativa a un diverso ambiente di esecuzione. 
Le macchine virtuali di questi ambienti di esecuzione sono inoltre basate su [Ubuntu](https://www.ubuntu-it.org/). 

In questo momento, dovrebbero essere presenti tutti gli ambienti utilizzati durante il corso, 
ma alcuni di questi ambienti potrebbero non essere stati ancora aggiornati. 
In tal caso, questi ambienti verranno aggiornati durante lo svolgimento del corso, 
ed è anche possibile che dei nuovi ambienti vengano aggiunti al repository. 

Questi ambienti di esecuzione possono essere utilizzati per 
eseguire i progetti delle applicazioni distribuite 
definiti nella cartella [projects/](../../projects/) del repository. 


## Software richiesto per l'utilizzo di VMware Workstation con Vagrant  

Ecco il software richiesto per l'utilizzo di questi ambienti. 

* [VMware Workstation Pro](https://www.vmware.com/products/desktop-hypervisor/workstation-and-fusion), versione 17.6.4
* [Vagrant](https://www.vagrantup.com/), versione 2.4.9
* [Vagrant VMware Utility](https://developer.hashicorp.com/vagrant/docs/providers/vmware/vagrant-vmware-utility), versione 1.0.24
* [Vagrant VMware Plugin](https://developer.hashicorp.com/vagrant/docs/providers/vmware/installation)
  
E' inoltre necessario essere consapevoli 
di alcune [limitazioni del supporto di Vagrant per VMware Workstation](https://developer.hashicorp.com/vagrant/docs/providers/vmware/known-issues), 
ed in particolare la possibilità che, durante l'avvio con Vagrant di macchine virtuali VMware, 
altre macchine virtuali VMware perdano temporaneamente la connettività di rete.  


## Preparazione 

Per usare delle versioni più recenti del software di sviluppo (come *JDK* e *Gradle*) 
potrebbe essere necessario modificare le prime righe dei relativi script di installazione 
(ad esempio, **asw/ubuntu-resources/java/setup-open-jdk.sh** per *Open JDK*), 
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

2. posizionarsi nella cartella dell'ambiente di interesse (ad esempio, **asw/vmware-ubuntu-environments/developer**)

3. per avviare o creare l'ambiente di esecuzione, usare il comando `vagrant up` 

4. per collegarsi con SSH a una macchina virtuale *VM* dell'ambiente, usare il comando `vagrant ssh VM`
    
E' anche possibile: 

* arrestare l'ambiente di esecuzione, con il comando `vagrant halt`

* distruggere l'ambiente di esecuzione, con il comando `vagrant destroy -f`  


## Ambienti 

* [developer](developer/):
  un ambiente per la compilazione e l'assemblaggio (build) dei progetti Java del repository, 
  nonché per l'esecuzione di queste applicazioni Java, 
  che consente anche l'esecuzione concorrente di molteplici applicazioni; 
  oltre al software di sviluppo per *Java*, ha anche *Docker*; 
  i progetti (in questo e anche negli altri ambienti) potranno essere trovati 
  nella cartella **/home/asw/projects/** oppure nella cartella **projects/** dell'utente di default
  
* [kube-cluster](kube-cluster/):
  un ambiente costituito da un cluster di nodi *Kubernetes* (*kube-1*, *kube-2* e *kube-3*)
  più un nodo per la compilazione dei progetti e per la costruzione delle immagini dei container *Docker*; 
  richiede una quantità notevole di risorse nel computer host;  
  sono raccomandati una CPU con almeno 4 core e 8 thread e una memoria RAM di almeno 16 GB 

