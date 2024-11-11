# Utilizzo di Kafka con Docker e Kubernetes 

Nel rilascio dell'applicazione *GoodMusic* con *Docker* e *Kubernetes*, 
uno degli aspetti più difficile è probabilmente l'utilizzo di *Kafka*. 

Queste brevi note dovrebbero semplificare lo svolgimento del progetto relativamente all'utilizzo di Kafka. 

## Utilizzo di Kafka con Docker 

Per l'esecuzione di Kafka con Docker si può utilizzare questo frammento di file [docker-compose.yml](docker-compose.yml). 

In particolare, quando le applicazioni Spring che implementano i servizi di *GoodMusic* sono eseguite nell'host (e non in container) 
dovranno configurare l'accesso a Kafka nel file *application.properties* 
impostando la proprietà *spring.kafka.bootstrap-servers* a *localhost:9092*. 
(In alternativa si può utilizzare una variabile d'ambiente *SPRING_KAFKA_BOOTSTRAP_SERVERS*.)

Tuttavia, quando le applicazioni Spring che implementano i servizi di *GoodMusic* sono eseguite in container Docker (e non direttamente nell'host) 
dovranno configurare l'accesso a Kafka nel file *application.properties* 
impostando la proprietà *spring.kafka.bootstrap-servers* a *kafka:9092*. 
(In alternativa si può utilizzare una variabile d'ambiente *SPRING_KAFKA_BOOTSTRAP_SERVERS*.)
## Utilizzo di Kafka con Kubernetes 

L'immagine Docker *bitnami/kafka* per Kafka che può essere utilizzata nell'esecuzione dell'applicazione con *Docker* e *Docker Compose* 
non è adatta per il rilascio con *Kubernetes*. 

Un modo semplice per eseguire Kafka in Kubernetes è utilizzare [Helm](https://helm.sh/), 
che è già installato nell'ambiente *kube-cluster*, 
insieme al [package Helm per Kafka](https://bitnami.com/stack/kafka/helm). 
In pratica, vanno usati il file di configurazione [kafka-values.yaml](kafka-values.yaml) e [kafka-provisioning.yaml](kafka-provisioning.yaml) 
ed eseguito lo script [deploy-kafka.sh](deploy-kafka.sh) per effetture il rilascio di Kafka in Kubernetes con Helm. 

Alcune osservazioni importanti: 
* il file di configurazione [kafka-values.yaml](kafka-values.yaml) può essere usato così come è, 
  ma bisogna sapere che definisce una configurazione estremamente semplificata di Kafka, senza controllo della sicurezza né persistenza 
* il file di configurazione [kafka-provisioning.yaml](kafka-provisioning.yaml), prima di poter essere utilizzato, 
  va personalizzato per dichiarare i topic Kafka che si intendono usare nell'applicazione 
  (come guida sono forniti degli esempi, ma relativi a un dominio differente)

Quando le applicazioni Spring che implementano i servizi di *GoodMusic* sono eseguite in pod Kubernetes  
dovranno configurare l'accesso a Kafka nel file *application.properties* 
impostando la proprietà *spring.kafka.bootstrap-servers* a *kafka.kafka:9092*. 
(In alternativa si può utilizzare una variabile d'ambiente *SPRING_KAFKA_BOOTSTRAP_SERVERS*.)
