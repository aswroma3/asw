# Progetti del corso di Architettura dei Sistemi Software 

Questa sezione del repository contiene il codice (codice sorgente) 
di alcune semplici *applicazioni software distribuite* e basate su *middleware*. 
Le diverse applicazioni distribuite sono organizzate in progetti 
(rappresentati da sottocartelle in questa sezione del repository), 
ciascuno dei quali è relativo a un argomento delle esercitazioni del corso. 

In questo momento, potrebbero non essere ancora presenti tutti i progetti e tutte le applicazioni, 
oppure alcuni dei progetti presenti potrebbero non essere stati aggiornati. 
In tal caso, questi progetti verranno aggiunti al repository oppure aggiornati durante lo svolgimento del corso. 

L'esecuzione delle applicazioni distribuite deve essere effettuata 
in un opportuno *ambiente di esecuzione*, 
definito nella cartella [environments/](../environments/) del repository. 

## Contenuto dei progetti 

I **progetti** si trovano, in ogni ambiente di esecuzione, 
nella cartella **/home/asw/projects/** oppure nella cartella **projects/** dell'utente di default. 
Ciascun **progetto** contiene (come sotto-cartelle) una o più **applicazioni distribuite**. 
Ogni applicazione distribuita è composta da uno o più **moduli**, 
che danno luogo a uno o più **componenti eseguibili** dell'applicazione
(per esempio, un *client* e un *server*). 
In generale, ogni applicazione va eseguita in un **ambiente di esecuzione** opportuno, 
e in particolare ogni componente eseguibile dell'applicazione 
va eseguito in una macchina virtuale opportuna dell'ambiente. 

Queste informazioni sono descritte nell'ambito di ciascun progetto. 

## Build  

La costruzione (build, ovvero compilazione e assemblaggio) delle applicazioni 
va fatta applicazione per applicazione, utilizzando **Java SDK** e **Gradle**. 

Per compilare un'applicazione sul proprio PC bisogna: 

1. aver preventivamente installato e configurato *Java SDK* e *Gradle* 

2. usare una shell/terminale del proprio PC 

3. posizionarsi nella cartella principale dell'applicazione di interesse 

4. per compilare e assemblare l'applicazione, eseguire il comando `gradle build` 

In alternativa, è possibile compilare un'applicazione in una macchina virtuale:   

1. collegarsi con `vagrant ssh` alla macchina virtuale **workstation** 
   dell'ambiente [workstation](../environments/workstation/), su cui sono installati *Java SDK* e *Gradle* 

2. posizionarsi nella cartella principale dell'applicazione di interesse 

3. per compilare e assemblare l'applicazione, eseguire il comando `gradle build` 

E' anche possibile: 

* ripulire la build di un'applicazione, con il comando `gradle clean`


## Esecuzione 

Il risultato della costruzione di un'applicazione 
è in generale composto da uno o più **componenti eseguibili**, 
che dopo la costruzione sono disponibili nella cartella **build** di ciascun componente eseguibile dell'applicazione, 
contenente i file *jar* dell'applicazione ed eventuali file di configurazione. 

In generale, ciascun componente eseguibile va poi mandato in esecuzione 
nell'ambito di una opportuna macchina virtuale di un opportuno ambiente di esecuzione. 

La modalità specifica di esecuzione delle applicazioni distribuite può variare da progetto a progetto, 
e pertanto è descritta nell'ambito dei singoli progetti. 

Nei casi più semplici, è possibile eseguire un'applicazione come segue:  

2. posizionarsi nella cartella principale dell'applicazione di interesse 

3. per eseguire l'applicazione, eseguire il comando `gradle run` 


## Progetti 

* [asw-000-ciao-mondo](asw-000-ciao-mondo/): un semplice progetto di prova 

* [asw-810-introduzione-connettori](asw-810-introduzione-connettori/): introduzione ai connettori  

* [asw-815-connettori-distribuiti](asw-815-connettori-distribuiti/): connettori distribuiti e comunicazione client-server 

* [asw-820-spring-framework](asw-820-spring-framework/): introduzione a *Spring* 

* [asw-825-spring-boot](asw-825-spring-boot/): *Spring Boot* 

* [asw-830-grpc](asw-830-grpc/): invocazione remota con *gRPC* 

* [asw-835-rest](asw-835-rest/): invocazione remota con *REST* 

* [asw-840-kafka](asw-840-kafka/): comunicazione asincrona con *Kafka*

* [asw-850-spring-cloud](asw-850-spring-cloud/): *Spring Cloud* 

<!---

  nient'altro da escludere 
-->


## Progetti Vagrant 

* [asw-870-strumenti-ambienti-virtuali](asw-870-strumenti-ambienti-virtuali/): gestione di ambienti virtuali con *Vagrant* e *Oracle VM VirtualBox* 


<!---

  nient'altro da escludere 
-->

## Progetti Docker 

* [asw-880-docker](asw-880-docker/): introduzione a *Docker* 

* [asw-885-docker-spring](asw-885-docker-spring/): esecuzione di applicazioni *Spring* con *Docker* 

<!---

* [asw-899-docker-su-swarm](asw-899-docker-su-swarm/): rilascio di un'applicazione multi-servizi e multi-container sullo swarm *swarm.inf.uniroma3.it*
-->

## Progetti Kubernetes 


* [asw-890-kubernetes](asw-890-kubernetes/): *orchestrazione* di container con *Kubernetes* 

<!---

  nient'altro da escludere 
-->


## Progetti per l'anno accademico 2023-2024 

<!---
* [asw-goodbooks](asw-goodbooks/): *GoodBooks*: un semplice social network per la condivisione di recensioni di libri 

* [asw-edipogram](asw-edipogram/): *Edipogram*: un semplice social network per la condivisione di enigmi e giochi enigmistici 

* [asw-sfingegram](asw-sfingegram/): *Sfingegram*: un semplice social network per la condivisione di enigmi (ovvero, giochi enigmistici) 

* [asw-instagnam](asw-instagnam/): *Instagnam*: un semplice social network per la condivisione di ricette di cucina 
-->

* [asw-order-manager](asw-order-manager/): *OrderManager*: una semplice applicazione a microservizi per la gestione di ordini di prodotti 

<!---
* [asw-895-docker-orchestrazione](asw-895-docker-orchestrazione/): *composizione* e *orchestrazione* di *contenitori Docker* 

* [asw-899-docker-su-swarm](asw-899-docker-su-swarm/): rilascio di un'applicazione multi-servizi e multi-contenitori sullo swarm *swarm.inf.uniroma3.it*
-->


