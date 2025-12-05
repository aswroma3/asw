# Sentence (Kubernetes, usando il DNS al posto del servizio di service discovery)

## Progetto realizzato a partire da asw-890-kubernetes/l-sentence-kube-dockerhub

## Modifiche effettuate rispetto al progetto asw-890-kubernetes/l-sentence-kube-dockerhub

Nel codice: 
* NESSUNA 

Nella configurazione delle applicazioni (application.yml): 
* nei servizi *sentence* e *sentence-async*: 
  cambiare asw.sentence.sentenceservice.serviceIdToUriFormat in http://%s:8080
  (e.g., vede i servizi come http://subject:8080 anziché http://subject) 
* nell'apigateway: 
  usare http://sentence:8080, http://subject:8080, http://verb:8080 e http://object:8080 
  anziché lb://sentence, lb://subject, lb://verb e lb://object 

Nei file GRADLE: 
* rimossa la dipendenza spring-cloud-starter-kubernetes-client-loadbalancer

