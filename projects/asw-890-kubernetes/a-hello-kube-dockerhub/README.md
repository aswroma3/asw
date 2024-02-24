# Hello (codice sorgente)

Questo sottoprogetto contiene il codice per una semplice applicazione web Spring Boot da eseguire su Kubernetes. 

In particolare, questo sottoprogetto contiene solo il codice sorgente e di configurazione, i file di configurazione Docker e gli script per la costruzione delle immagini Docker e per effettuare il push della immagini Docker su Docker Hub. 
Tuttavia, questo sottoprogetto non contiene i file di specifica per il rilascio su Kubernetes (presenti in sottoprogetti successivi). 

## Build Java - OPZIONALE 

*Questo passo è necessario solo se si vogliono utilizzare delle immagini Docker diverse da quelle predisposte dal docente del corso.*

Per la costruzione dell'applicazione, eseguire il comando `gradle assemble` oppure `gradle build`

## Build (Docker) - OPZIONALE 

*Questo passo è necessario solo se si vogliono utilizzare delle immagini Docker diverse da quelle predisposte dal docente del corso.*

Per la costruzione delle immagini Docker ed effettuare il push su Docker Hub: 

* accedere a Docker Hub, eseguendo il comando `docker login` (è necessaria la registrazione a Docker Hub)

* modificare il file `build-and-push-docker-image.sh` (e i file Kubernetes nei sottoprogetti successivi), usando il nome del proprio account su Docker Hub al posto di **aswroma3** 

* eseguire lo script `build-and-push-docker-image.sh` 

## Ambiente di esecuzione 

Queste attività possono essere eseguite nell'ambiente [kube-cluster](../../environments/kube-cluster/), sul nodo **kube-dev**. 

