# Sentence (Kubernetes, usando il DNS al posto del servizio di service discovery)

## Progetto realizzato a partire da asw-890-kubernetes/l-sentence-kube-dockerhub

## Modifiche effettuate rispetto al progetto asw-890-kubernetes/l-sentence-kube-dockerhub

* Modifiche al codice (ma usando delle proprietà il codice potrebbe forse essere lo stesso)

  * nei servizi *sentence* e *sentence-async*, 
    usare http://subject:8080, http://verb:8080 e http://object:8080 anziché http://subject, http://verb e http://object 

* Dipendenze 

  * rimossa la dipendenza spring-cloud-starter-kubernetes-client-loadbalancer

* Nella configurazione dell'applicazione api-gateway (application.yml) 
  
  * usare http://sentence:8080, http://subject:8080, http://verb:8080 e http://object:8080 
    anziché lb://sentence, lb://subject, lb://verb e lb://object 
