# Sentence (versione per Kubernetes)

## Progetto realizzato a partire da asw-875-docker-spring/b-sentence-docker

## Modifiche effettuate rispetto al progetto asw-875-docker-spring/b-sentence-docker

Nel codice: 
* NESSUNA 

Nei file GRADLE: 
* rimossa la dipendenza starter per consul 
* sostituita la dipendenza spring-cloud-starter-loadbalancer con spring-cloud-starter-kubernetes-client-loadbalancer
  
Nei Dockerfile: 
* levata la clausola HEALTHCHECK, non utilizzata da Kubernetes 

Nella configurazione delle applicazioni (application.yml): 
* bisogna levare la parte riguardante consul (che non viene più utilizzato) 
* non serve nessuna configurazione per usare il servizio di service discovery di Kubernetes 
