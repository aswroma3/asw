# Sentence (versione per Kubernetes)

## Progetto realizzato a partire da asw-885-docker-spring/b-sentence-docker

## Modifiche effettuate rispetto al progetto asw-885-docker-spring/b-sentence-docker

* Nessuna modifica al codice 

* Dipendenze 

  * rimossa la dipendenza starter per consul 
  
  * aggiunta la dipendenza spring-cloud-starter-kubernetes-client-loadbalancer

* Nelle configurazioni delle applicazioni (application.yml) 
  
  * bisogna levare la parte riguardante consul (che non viene più utilizzato) 
  
  * non serve nessuna configurazione per usare il servizio di service discovery di Kubernetes 
  
* Nei Dockerfile 

  * levata la clausola HEALTHCHECK, non utilizzata da Kubernetes 