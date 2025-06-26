# Developer (versione per VirtualBox basata su Ubuntu) 

Questo è un ambiente di esecuzione pensato per lo sviluppo di applicazioni Java distribuite, 
ovvero per la compilazione e l'esecuzione di progetti Java. 

Questo ambiente ha le seguenti caratteristiche: 

* ha una configurazione potente in termini di memoria e processore, 
  per consentire anche l'esecuzione concorrente di molteplici applicazioni
  
* oltre al software di sviluppo per *Java* ha anche *Docker* 
  (utile, per esempio, se si vuole eseguire qualcosa in un container)
  
E' composto da una sola macchina virtuale **dev**. 


## Descrizione delle macchine virtuali 

### dev

La macchina virtuale **dev** ha il seguente software 

* Ubuntu 24.04 LTS a 64 bit (by Bento) 

* Java SDK (Open JDK)
  
* Gradle 

* Apache Maven 

* Python 

* Docker e Docker Compose 

Configurazione di rete 

* Indirizzo IP: 10.11.1.121 

* Porte pubblicate sull'host: 8080 -> 8080 (http) 
  <!-- , nonché 9092 -> 9092 (Kafka), 5432 -> 5432 (Postgres) -->

Hardware (virtuale) 

* Memoria: 4096 MB (4.0 GB) - si può ridurre a 2048 nei casi più semplici

* Virtual CPU: 4 - si puo' ridurre a 2 nei casi più semplici 


## Tempo di preparazione dell'ambiente 

Tempo di primo provisioning dell'ambiente: circa 5-15 minuti 
