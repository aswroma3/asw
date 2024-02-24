# Comunicazione asincrona con Kafka (asw-840)

Questo progetto contiene alcune applicazioni distribuite basate sulla **comunicazione asincrona** con **Kafka**: 

* **a-simple-messaging** è una semplice applicazione con un produttore di messaggi e un consumatore di messaggi 
  
* **b-simple-pipeline** è una semplice applicazione con un produttore di messaggi, un filtro di messaggi e un consumatore di messaggi 
  
* **c-restaurant** è l'applicazione per la gestione di ristoranti (già definita in un precedente progetto), con cui è ora possibile interagire anche in modo asincrono 

Le diverse applicazioni hanno una struttura simile, e la loro costruzione ed esecuzione è descritta qui di seguito. 


## Build  

Per la costruzione di ciascuna applicazione, vedere le istruzioni descritte nella sezione [projects/](../). 

In pratica, per compilare e assemblare ciascuna applicazione, bisogna 

1. posizionarsi nella cartella principale dell'applicazione di interesse - ad esempio `~/projects/asw-840-kafka/a-simple-messaging`

2. per compilare e assemblare l'applicazione, usare il comando `gradle build` 


## Componenti eseguibili 

Queste applicazioni sono tutte composte da due o più componenti eseguibili 
(ad esempio, un componente produttore, un componente filtro e un componente consumatore). 
Per la descrizione dei componenti, vedere il file README nella cartella di ciascun progetto. 


### Ambiente di esecuzione 

Queste applicazioni vanno eseguite nell'ambiente [workstation](../../environments/workstation/), sul nodo **workstation**. 
Vanno però utilizzate più finestre (terminali) diverse, una per ciascun componente eseguibile dell'applicazione. 


### Esecuzione 

Per l'esecuzione delle diverse applicazioni, vedere il file README nella cartella di ciascun progetto. 
