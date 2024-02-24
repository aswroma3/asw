# Workstation 

Questo � un ambiente di esecuzione pensato per la compilazione dei progetti, 
nonch� per l'esecuzione di applicazioni Java distribuite, 
con le seguenti caratteristiche: 

* ha una configurazione potente in termini di memoria e processore, 
  per consentire anche l'esecuzione concorrente di molteplici applicazioni
  
* oltre al software di sviluppo per *Java* ha anche *Docker* 
  (utile, per esempio, se si vuole eseguire qualcosa in un container)
  
E' composto da una sola macchina virtuale **workstation**. 

## Descrizione delle macchine virtuali 

### workstation

La macchina virtuale **workstation** ha il seguente software 

* Ubuntu 22.04 LTS a 64 bit (by Bento) 

* Java SDK (Open JDK)
  
* Gradle 

* Apache Maven 

* Docker e Docker Compose 

Configurazione di rete 

* Indirizzo IP: 10.11.1.121 

* Porte pubblicate sull'host: 8080 -> 8080 (http) 
  <!-- , nonch� 9092 -> 9092 (Kafka), 5432 -> 5432 (Postgres) -->

Hardware (virtuale) 

* Memoria: 4096 MB (4.0 GB) - si pu� ridurre a 2048 nei casi pi� semplici

* Virtual CPU: 4 - si puo' ridurre a 2 nei casi pi� semplici 


## Tempo di preparazione dell'ambiente 

Tempo di primo provisioning dell'ambiente: circa 5-20 minuti 
