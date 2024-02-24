# Utilizzo di Kafka con Kubernetes 

Nel rilascio dell'applicazione *OrderManager* in *Kubernetes*, 
l'aspetto più difficile è probabilmente l'utilizzo di Kafka. 
In particolare, l'immagine Docker *bitnami/kafka* utilizzata nelle esercitazioni 
e che funziona correttamente con *Docker* e *Docker Compose* 
non è invece adatta per il rilascio con *Kubernetes*. 

Per semplificare lo svolgimento del progetto, relativamente al rilascio con *Kubernetes*, 
viene qui fornita la specifica delle risorse Kubernetes per *Kafka* [kafka.yaml](kafka.yaml)
basata sull'immagine Docker *wurstmeister/kafka* che, seppur un po' datata, funziona correttamente con Kubernetes. 

Questa specifica di risorse va installata nello stesso namespace delle altre risorse dell'applicazione. 

Utilizzando questa specifica di risorse, 
nelle applicazioni Spring si potrà accedere a Kafka 
impostando la proprietà *spring.kafka.bootstrap-servers* al valore *kafka:9092*. 

In alternativa, se si vuole rilasciare le risorse per Kafka in un namespace di nome *kafka*, bisognerà procedere come segue: 

* nel file [kafka.yaml](kafka.yaml), la variabile d'ambiente *KAFKA_ADVERTISED_HOST_NAME* va impostata al valore *kafka.kafka* anziché *kafka*

* dalle applicazioni Spring si potrà accedere a Kafka impostando la proprietà *spring.kafka.bootstrap-servers* al valore *kafka.kafka:9092* anziché *kafka:9092*