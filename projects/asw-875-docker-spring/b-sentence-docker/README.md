# Sentence (versione per Docker)

Questo sottoprogetto mostra una versione dell'applicazione **sentence** per *Docker*. 

## Costruzione ed esecuzione 

### Build (Java) 

Dalla cartella principale dell'applicazione, usare il comando `gradle build` oppure lo script `build-java-projects.sh`

### Build (Docker) 

Dalla cartella principale dell'applicazione, usare lo script `build-docker-images.sh`

Opazionalmente, eseguire anche lo script `tag-and-push-docker-images.sh` (richiede di aver effettuato il login su Docker Hub con il comando `docker login`)

### Esecuzione 

Dalla cartella principale dell'applicazione, usare lo script `run-sentence-docker.sh`

In alternativa si può usare lo script `run-sentence-docker-from-docker-hub.sh`, che non richiede di aver effettuato localmente la build delle immagini Docker 

Per verificare che l'applicazione sia stata avviata correttamente (ci vuole circa un minuto) usare il comando `docker ps` per verificare che lo *status* dei diversi container sia *healthy* e non *starting* 

L'applicazione può essere verificata usando lo script `run-curl-client.sh` oppure `run-curl-client.sh N` per invocare il servizio *N* volte 

Alla fine, l'applicazione può essere arrestata usando lo script `stop-and-remove-sentence-containers.sh`  


### Esecuzione con più istanze dei container  

Usare lo script `run-sentence-docker-replicated.sh` oppure lo script `run-sentence-docker-replicated-from-docker-hub.sh`

