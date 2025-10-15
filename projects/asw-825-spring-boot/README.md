# Spring Boot (asw-825)

Questo progetto contiene alcune applicazioni 
che esemplificano l'uso di [Spring Boot](https://spring.io/projects/spring-boot):  

* **a-hello** è un esempio minimale di applicazione Spring Boot  

* **b-hello-web** è un esempio minimale di applicazione web Spring Boot 

* **c-hello-web-mvc** esemplifica Spring Web MVC 

* **d-restaurants** è un'applicazione per la gestione di ristoranti, che esemplifica l'uso di Spring Data JPA (nel contesto di un'applicazione Spring Web MVC)

* **e-restaurants-with-menu** estende l'applicazione precedente con la gestione dei menu dei ristoranti 

* **f-restaurants-postgresql** esemplifica l'uso PostgreSQL con Spring Boot  

* **g-restaurants-actuator** esemplifica l'uso di Spring Boot Actuator 

* **h-lucky-word-properties** esemplifica l'uso delle proprietà per la configurazione delle applicazioni 

* **i-lucky-word-profiles** esemplifica l'uso dei profili per la configurazione delle applicazioni

Le diverse applicazioni hanno una struttura simile, 
e la loro costruzione ed esecuzione è descritta qui di seguito. 

### Build  

Per la costruzione di ciascuna applicazione, vedere le istruzioni 
descritte nella sezione [projects/](../). 

In pratica, per compilare e assemblare ciascuna applicazione, bisogna 

1. posizionarsi nella cartella principale dell'applicazione di interesse - ad esempio `~/projects/asw-825-spring-boot/a-hello`

2. per compilare e assemblare l'applicazione, usare il comando `gradle build` 

### Componenti eseguibili 

Ciascuna di queste applicazioni è composta da un unico componente eseguibile. 
Le applicazioni web possono essere accedute sulla porta **8080** dell'host, 
sia se eseguite sul proprio PC che se vengono eseguite 
nel nodo **dev** di un ambiente **developer**. 

### Ambiente di esecuzione 

Ciascuna di queste applicazioni può essere eseguita direttamente sul proprio PC,
oppure nel nodo **dev** di un ambiente **developer**. 
In questo modo, però, le applicazioni web che espongono servizi alla porta **8080** del nodo **dev** 
vengono pubblicate sulla porta **8080** dell'host. 

### Esecuzione 

Per eseguire un'applicazione (ad eccezione delle applicazioni 
**f-restaurants-postgresql**, **h-lucky-word-properties** e **i-lucky-word-profiles**, descritte più avanti): 

1. posizionarsi nella cartella principale dell'applicazione di interesse - ad esempio `~/projects/asw-825-spring-boot/a-hello`

2. eseguire il comando `gradle bootRun &` (attenzione, bisogna usare il task `bootRun` di Gradle, e non il task `run`)

#### Esecuzione dell'applicazione **f-restaurants-postgresql** 

Posizionarsi nella cartella principale dell'applicazione, `~/projects/asw-825-spring-boot/f-restaurants-postgresql` e poi: 

* avviare PostgreSQL eseguendo lo script `./start-postgres.sh` 

* avviare l'applicazione eseguendo il comando `gradle bootRun &` 

* dopo l'esecuzione dell'applicazione, arrestare PostgreSQL eseguendo lo script `./stop-postgres.sh` 

#### Esecuzione dell'applicazione **h-lucky-word-properties** 

Posizionarsi nella cartella principale dell'applicazione, `~/projects/asw-825-spring-boot/h-lucky-word-properties` e poi: 

* eseguire lo script `./run-with-default-property.sh` (oppure il comando `gradle bootRun`) 
  per avviare il servizio usando la parola fortunata di default (la parola fortunata è *Default*)

* eseguire lo script `./run-with-argument.sh` (oppure il comando `gradle bootRun --args="--lucky.word=Argument"`)
  per avviare il servizio passando la parola fortunata come un argomento (la parola fortunata è *Argument*)

* eseguire lo script `./run-with-env-variable.sh` (oppure il comando `LUCKY_WORD="Environment variable" gradle bootRun`)
  per avviare il servizio passando la parola fortunata mediante una variabile d'ambiente (la parola fortunata è *Environment variable*)

Per accedere alla parola fortunata è possibile: 

* usare un browser web ed accedere all'URL `http://localhost:8080/lucky-word` 

* oppure, eseguire lo script `./run-curl-client.sh` che accede all'URL precedente mediante *curl* 

#### Esecuzione dell'applicazione **i-lucky-word-profiles** 

Posizionarsi nella cartella principale dell'applicazione, `~/projects/asw-825-spring-boot/i-lucky-word-profiles` e poi: 

* eseguire lo script `./run-as-default.sh` (oppure il comando `gradle bootRun`)
  per avviare il servizio usando il profilo di default (la parola fortunata è *Default*)

* eseguire lo script `./run-as-english.sh` (oppure il comando `SPRING_PROFILES_ACTIVE="english" gradle bootRun`)
  per avviare il servizio usando il profilo **english** (la parola fortunata è *Happy*)

* eseguire lo script `./run-as-italian.sh` (oppure il comando `SPRING_PROFILES_ACTIVE="italian" gradle bootRun`)
  per avviare il servizio usando il profilo **italian** (la parola fortunata è *Evviva*)

