# Sentence (versione per Docker Compose)

Questo sottoprogetto mostra una versione dell'applicazione **sentence** per *Docker Compose*. 

## Costruzione ed esecuzione 

### Build (Java) 

Dalla cartella principale dell'applicazione, usare il comando `gradle build` oppure lo script `build-java-projects.sh`

### Build (Docker) 

Dalla cartella principale dell'applicazione, usare il comando `docker compose -f docker-compose.yml build` oppure lo script `build-docker-images-with-compose.sh`

Volendo, eseguire anche lo script `push-docker-images-with-compose.sh` (richiede di aver effettuato il login su Docker Hub con il comando `docker login`)

### Esecuzione 

Dalla cartella principale dell'applicazione, usare il comando `docker compose -f docker-compose.yml up` oppure lo script `run-sentence-compose.sh`

L'applicazione può essere verificata usando lo script `run-curl-client.sh` oppure `run-curl-client.sh N` per invocare il servizio *N* volte 

Alla fine, l'applicazione può essere arrestata usando il comando `docker compose -f docker-compose.yml down` oppure lo script `stop-sentence-compose.sh`  


### Esecuzione con più istanze dei container  

Usare gli script `scale-sentence-up.sh` e `scale-sentence-down.sh`

