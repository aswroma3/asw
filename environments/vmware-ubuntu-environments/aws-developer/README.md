# AWS Developer (versione per VMware Workstation basata su Ubuntu) 

Questo è un ambiente di esecuzione pensato per supportare l'accesso a un cluster [Kubernetes](https://kubernetes.io/) 
basato sul servizio [Amazon Elastic Kubernetes Service (EKS)](https://aws.amazon.com/it/eks/). 

E' composto da una sola macchina virtuale **aws-dev**. 


## Descrizione delle macchine virtuali 

### aws-dev

La macchina virtuale **aws-dev** ha il seguente software 

* Ubuntu 24.04 LTS a 64 bit (by Bento) 

* software di supporto per Kubernetes: Kubectl e Helm 

* software di supporto all'accesso di Amazon EKS: AWS CLI e Eksctl 

E' inoltre predisposta per il seguente software 

* Java SDK (Open JDK)
  
* Gradle 

* Apache Maven 

* Python 

* Docker e Docker Compose 

Configurazione di rete 

* Indirizzo IP: 10.11.2.125 

* Porte pubblicate sull'host: 8080 -> 8080 (http) 
  <!-- , nonché 9092 -> 9092 (Kafka), 5432 -> 5432 (Postgres) -->

Hardware (virtuale) 

* Memoria: 2024 MB (2.0 GB) - si può aumentare a 4096 se serve 

* Virtual CPU: 2 - si può aumentare a 4 se serve 


## Tempo di preparazione dell'ambiente 

Tempo di primo provisioning dell'ambiente: circa 5-20 minuti 
