# Strumenti per la gestione di ambienti virtuali (asw-860, versione per VMware Workstation) 

Questo "progetto" contiene la definizione di tre diversi *ambienti virtuali*, 
tutti basati sull'uso di *strumenti per la gestione di ambienti virtuali* 
come **Vagrant** e **VMware Workstation**. 
Ciascuno di questi ambienti è composto da una singola macchina virtuale con *Apache HTTP Server*. 
Nei diversi ambienti, il provisioning della macchina virtuale viene effettuato in tre modi differenti. 


## Ambiente di esecuzione 

I "progetti" proposti devono essere "eseguiti" direttamente **nel PC host**, e **non in una macchina virtuale**. 


## Descrizione degli ambienti  

Ciascuno dei seguenti progetti Vagrant definisce un diverso ambiente, che deve essere creato dall'host usando il comando `vagrant up`


### a-apache-shell 

Nell'ambiente [a-apache-shell](a-apache-shell/)
il provisioning della macchina virtuale viene effettuato mediante l'uso di uno *script bash*. 

La macchina virtuale **default** ha il seguente software: 

* Ubuntu 24.04 LTS a 64 bit (by Bento) 

* Apache HTTP Server - installato mediante uno *script bash* 

Configurazione di rete 

* Indirizzo IP: 10.11.2.191 

* Porte pubblicate sull'host: 80 -> 8081 

Per verificare il funzionamento, accedere alla pagina web [localhost:8081](http://localhost:8081) 


### b-apache-puppet 

Nell'ambiente [b-apache-puppet](b-apache-puppet/)
il provisioning della macchina virtuale viene effettuato mediante l'uso di *Puppet Apply*. 

La macchina virtuale **default** ha il seguente software: 

* Ubuntu 24.04 LTS a 64 bit (by Bento) 

* Puppet Apply - installato mediante uno *script bash* 

* Apache HTTP Server - installato mediante *Puppet Apply* 

Configurazione di rete 

* Indirizzo IP: 10.11.2.192 

* Porte pubblicate sull'host: 80 -> 8082 
  
Per verificare il funzionamento, accedere alla pagina web [localhost:8082](http://localhost:8082) 

  
### c-apache-puppet-modules 

Nell'ambiente [c-apache-puppet-modules](c-apache-puppet-modules/)
il provisioning della macchina virtuale viene effettuato mediante l'uso di *Puppet Apply*, 
sulla base del modulo predefinito *puppetlabs/apache*. 

La macchina virtuale **default** ha il seguente software: 

* Ubuntu 24.04 LTS a 64 bit (by Bento) 

* Puppet Apply - installato mediante uno *script bash* 

* Apache HTTP Server - installato mediante *Puppet Apply*, usando il modulo *puppetlabs/apache* e le sue dipendenze (scaricate mediante uno script bash)

Configurazione di rete 

* Indirizzo IP: 10.11.2.193 

* Porte pubblicate sull'host: 80 -> 8083 
  
Per verificare il funzionamento, accedere alla pagina web [localhost:8083](http://localhost:8083) 


