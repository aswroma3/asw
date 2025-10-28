# Invocazione remota con gRPC (asw-830)

Questo progetto contiene alcune applicazioni che esemplificano l'invocazione remota con [gRPC](https://grpc.io/):  

* **a-hello** è un esempio minimale di applicazione client-server basata su GRPC  

* **b-restaurants** è l'applicazione per la gestione di ristoranti (già definita in un precedente progetto),
  che ora espone i suoi servizi mediante gRPC e che ora include anche un client gRPC 

* **c-restaurants-with-menu** estende l'applicazione precedente con la gestione dei menu dei ristoranti 

Le diverse applicazioni hanno una struttura simile, e la loro costruzione ed esecuzione è descritta qui di seguito. 


### Build  

Per la costruzione di ciascuna applicazione, vedere le istruzioni descritte nella sezione [projects/](../). 

In pratica, per compilare e assemblare ciascuna applicazione, bisogna 

1. posizionarsi nella cartella principale dell'applicazione di interesse - ad esempio `~/projects/asw-830-grpc/a-hello`

2. per compilare e assemblare l'applicazione, usare il comando `gradle build` 


### Componenti eseguibili 

Queste applicazioni sono tutte composte da due componenti eseguibili 
(un componente server e un componente client). 


### Ambiente di esecuzione 

Ciascuna di queste applicazioni può essere eseguita direttamente sul proprio PC, 
oppure nel nodo **dev** di un ambiente **developer**. 
Vanno però utilizzate due finestre (terminali) diverse: 
una per il **server** e una per il **client**. 


### Esecuzione 

Per eseguire l'applicazione **a-hello** si proceda come segue: 

1. sulla finestra (terminale) nodo **server** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-830-grpc/a-hello`

   b. eseguire il comando `gradle hello-service:bootRun &` 
   
   c. il server può essere poi arrestato eseguendo lo script `stop-gradle-processes.sh` 

2. sulla finestra **client** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-830-grpc/a-hello`

   b. eseguire il comando `gradle hello-client:bootRun` (per utilizzare il blocking stub)
      oppure il comando ` ASW_GRPC_HELLO_SERVICE_BLOCKING=false gradle hello-client:bootRun` (per utilizzare il future stub)

Per eseguire le applicazioni **b-restaurants** e **c-restaurants-with-menu** si proceda come segue: 

1. sulla finestra (terminale) nodo **server** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-830-grpc/b-restaurants` oppure `~/projects/asw-830-grpc/c-restaurants-with-menu`

   b. eseguire il comando `gradle restaurant-service:bootRun &` 
   
   c. il server può essere poi arrestato eseguendo lo script `stop-gradle-processes.sh` 
      (**attenzione**: questo script arresta tutti i processi Gradle in esecuzione) 

2. sulla finestra **client** 

   a. posizionarsi nella cartella principale dell'applicazione `~/projects/asw-830-grpc/b-restaurants` oppure `~/projects/asw-830-grpc/c-restaurants-with-menu`

   b. eseguire il comando `gradle restaurant-client-grpc:bootRun` 
