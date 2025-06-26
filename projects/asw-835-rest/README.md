# Invocazione remota con REST (asw-835)

Questo progetto contiene alcune applicazioni che esemplificano l'invocazione remota con **REST**:  

* **a-hello** è un esempio minimale di applicazione client-server basata su REST  

* **b-restaurants** è l'applicazione per la gestione di ristoranti (già definita in un precedente progetto),
  che ora espone i suoi servizi mediante REST e con un client REST 

* **c-restaurants-with-menu** estende l'applicazione precedente con la gestione dei menu dei ristoranti 

Le diverse applicazioni hanno una struttura simile, 
e la loro costruzione ed esecuzione è descritta qui di seguito. 

### Build  

Per la costruzione di ciascuna applicazione, vedere le istruzioni 
descritte nella sezione [projects/](../). 

In pratica, per compilare e assemblare ciascuna applicazione, bisogna 

1. posizionarsi nella cartella principale dell'applicazione di interesse - ad esempio `~/projects/asw-835-rest/a-hello`

2. per compilare e assemblare l'applicazione, usare il comando `gradle build` 

### Componenti eseguibili 

Queste applicazioni sono tutte composte da due componenti eseguibili (un componente server e un componente client). 
Le applicazioni web possono essere accedute sulla porta **8080** dell'host, 
sia se eseguite sul proprio PC che se vengono eseguite 
nel nodo **dev** di un ambiente **developer**. 

### Ambiente di esecuzione 

Ciascuna di queste applicazioni può essere eseguita direttamente sul proprio PC, 
oppure nel nodo **dev** di un ambiente **developer**. 
Vanno però utilizzate due finestre (terminali) diverse: una per il **server** e una per il **client**. 

### Esecuzione 

Per eseguire l'applicazione **a-hello** si proceda come segue: 

1. sulla finestra (terminale) nodo **server** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-835-rest/a-hello`

   b. eseguire il comando `gradle hello-server:bootRun` 
   
   c. il server può essere arrestato con CTRL-C 

2. sulla finestra **client** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-835-rest/a-hello`

   b. eseguire il comando `gradle hello-client:bootRun` (basato su *WebClient*)
   
   c. per utilizzare invece il client basato su *RestTemplate*, eseguire il comando `gradle hello-client-resttemplate:bootRun` 

   d. in alternativa, usare il comando `./run-curl-client.sh` per eseguire un semplice client bash/curl anziché il client Java 

   e. come ulteriore alternativa, è anche possibile usare il comando `./run-python-client.py` per eseguire un semplice client Python anziché il client Java 

Per eseguire le applicazioni **b-restaurants** e **c-restaurants-with-menu** si proceda come segue: 

1. sulla finestra (terminale) nodo **server** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-835-rest/b-restaurants` oppure `~/projects/asw-835-rest/c-restaurants-with-menu`

   b. eseguire il comando `gradle restaurant-server:bootRun` 
   
   c. il server può essere arrestato con CTRL-C 

2. sulla finestra **client** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-835-rest/b-restaurants` oppure `~/projects/asw-835-rest/c-restaurants-with-menu`

   b. eseguire il comando `gradle restaurant-client-rest:bootRun` (utilizza il client sincrono) 
   
   c. per utilizzare il client asincrono, eseguire il comando `gradle restaurant-async-client-rest:bootRun` 

   d. in alternativa, usare il comando `./run-python-client.py` per eseguire un client Python (semplificato) anziché il client Java 
