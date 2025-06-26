# Sentence (versione per Kubernetes)

## Progetto realizzato a partire da asw-875-docker-spring/b-sentence-docker

## Modifiche effettuate rispetto al progetto asw-875-docker-spring/b-sentence-docker

Nel codice: 
* NESSUNA 

Nella configurazione delle applicazioni (application.yml): 
* bisogna levare la parte riguardante consul (che non viene più utilizzato) 
* non serve nessuna configurazione per usare il servizio di service discovery di Kubernetes 

Nei file GRADLE: 
* rimossa la dipendenza starter per consul 
* aggiunta la dipendenza spring-cloud-starter-kubernetes-client-loadbalancer
  
Inoltre: 
* nei Dockefile, levata la clausola HEALTHCHECK, non utilizzata da Kubernetes 
