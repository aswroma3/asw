# Utilizzo di Kafka con Docker e Kubernetes 

Nel rilascio dell'applicazione *BetterMusic* con *Docker* e *Kubernetes*, 
uno degli aspetti più difficile è probabilmente l'utilizzo di *Kafka*. 

Queste brevi note dovrebbero semplificare lo svolgimento del progetto relativamente all'utilizzo di Kafka. 

## Utilizzo di Kafka con Docker 

Per l'esecuzione di Kafka con Docker si può utilizzare il frammento di file [docker/docker-compose.yml](docker/docker-compose.yml), 
insieme agli script [docker/start-kafka.sh](docker/start-kafka.sh) e [docker/stop-kafka.sh](docker/stop-kafka.sh).
Lo script [docker/start-kafka.sh](docker/start-kafka.sh) va personalizzato indicando quali sono i topic da creare per l'applicazione
(come guida sono forniti degli esempi, ma relativi a un dominio differente). 

Inoltre, quando le applicazioni Spring che implementano i servizi di *BetterMusic* sono eseguite nell'host (e non in container) 
dovranno configurare l'accesso a Kafka nel file *application.properties* 
impostando la proprietà *spring.kafka.bootstrap-servers* a *localhost:9092*. 
(In alternativa si può utilizzare una variabile d'ambiente *SPRING_KAFKA_BOOTSTRAP_SERVERS*.)

Invece, quando le applicazioni Spring che implementano i servizi di *BetterMusic* sono eseguite in container Docker (e non direttamente nell'host) 
dovranno configurare l'accesso a Kafka nel file *application.properties* 
impostando la proprietà *spring.kafka.bootstrap-servers* a *kafka:9092*. 
(In alternativa si può utilizzare una variabile d'ambiente *SPRING_KAFKA_BOOTSTRAP_SERVERS*.)

## Utilizzo di Kafka con Kubernetes 

L'immagine Docker [bitnamilegacy/kafka](https://hub.docker.com/r/bitnamilegacy/kafka) per Kafka 
che può essere utilizzata nell'esecuzione dell'applicazione con *Docker* e *Docker Compose* 
non è adatta per il rilascio con *Kubernetes*. 

Un modo semplice per eseguire Kafka in Kubernetes è utilizzare [Helm](https://helm.sh/), 
che è già installato nell'ambiente *kube-cluster*, 
insieme al [package Helm per Kafka](https://github.com/bitnami/charts/tree/main/bitnami/kafka), 
che però va opportunamente personalizzato. 
In pratica, bisogna usare il file di configurazione [kubernetes/helm/my-kafka-values.yaml](kubernetes/helm/my-kafka-values.yaml)  
ed eseguito lo script [kubernetes/deploy-kafka.sh](kubernetes/deploy-kafka.sh) per effetture il rilascio di Kafka in Kubernetes con Helm. 

Alcune osservazioni importanti: 
* il file di configurazione [kubernetes/helm/my-kafka-values.yaml](kubernetes/helm/my-kafka-values.yaml) 
  definisce una configurazione estremamente semplificata di Kafka, senza controllo della sicurezza né persistenza 
* inoltre, questo file di configurazione [kubernetes/helm/my-kafka-values.yaml](kubernetes/helm/my-kafka-values.yaml) 
  va personalizzato nella sua parte finale per dichiarare i topic Kafka che si intendono usare nell'applicazione 
  (come guida sono forniti degli esempi, ma relativi a un dominio differente)

Quando le applicazioni Spring che implementano i servizi di *BetterMusic* sono eseguite in pod Kubernetes  
dovranno configurare l'accesso a Kafka nel file *application.properties* 
impostando la proprietà *spring.kafka.bootstrap-servers* a *kafka.kafka:9092*. 
(In alternativa si può utilizzare una variabile d'ambiente *SPRING_KAFKA_BOOTSTRAP_SERVERS*.)
