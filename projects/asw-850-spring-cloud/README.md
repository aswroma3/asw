# Spring Cloud (asw-850)

Questo progetto contiene alcune applicazioni che esemplificano l'uso di [Spring Cloud](https://spring.io/projects/spring-cloud) per la realizzazione di applicazioni *multi-servizi*. 

Gli esempi sono tutti relativi a un'applicazione distribuita **sentence**, il cui scopo è di generare delle semplici frasi casuali. 
L'applicazione viene mostrata in più versioni.  

* [a-sentence-basic](a-sentence-basic/): la versione di base  

* [b-sentence-config](b-sentence-config/): uso di un servizio di configurazione: [Spring Cloud Config](https://spring.io/projects/spring-cloud-config)

* [c-sentence-config-refresh](c-sentence-config-refresh/): uso di un servizio di configurazione, con refresh (statico e dinamico) delle configurazioni 

* [d-sentence-discovery-service](d-sentence-discovery-service/): uso di un servizio di service discovery: [Consul](https://www.consul.io/) e [Spring Cloud Consul](https://spring.io/projects/spring-cloud-consul) 

* [e-sentence-load-balancer](e-sentence-load-balancer/): uso di un load balancer lato client: [Spring Cloud LoadBalancer](https://spring.io/guides/gs/spring-cloud-loadbalancer/)

* [f-sentence-declarative-rest-client](f-sentence-declarative-rest-client/): uso di un client REST dichiarativo: [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)

* [g-sentence-circuit-breaker-retry](g-sentence-circuit-breaker-retry/): uso di circuit breaker e di retry: [Spring Cloud Circuit Breaker](https://spring.io/projects/spring-cloud-circuitbreaker) e [Resilience4J](https://github.com/resilience4j/resilience4j)

* [h-sentence-independent-circuit-breakers](h-sentence-independent-circuit-breakers/): uso di tre circuit breaker separati e indipendenti per i tre servizi per le parole 

* [i-sentence-apigateway](i-sentence-apigateway/): uso di un API gateway: [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)

* [j-sentence-apigateway-async](j-sentence-apigateway-async/): uso di chiamate REST asincrone e concorrenti 

Le diverse versioni di questa applicazione hanno una struttura simile (ma di volta in volta un po' diversa), e la loro costruzione ed esecuzione è descritta qui di seguito. 

Inoltre, il progetto [p-sentence-python-client](p-sentence-python-client/) fornisce un semplice client Python concorrente 
che può essere utilizzato con tutte le versioni dell'applicazione. 

## Build  

Per la costruzione di ciascuna versione dell'applicazione, bisogna 

1. posizionarsi nella cartella principale dell'applicazione di interesse - ad esempio `~/projects/asw-850-spring-cloud/a-sentence-basic`

2. per compilare e assemblare l'applicazione, usare il comando `gradle build` 

## Componenti eseguibili 

Ciascuna versione dell'applicazione è composta da due o più componenti eseguibili. 
Per la descrizione dei componenti eseguibili, vedere il file README nella cartella di ciascun sottoprogetto. 

### Ambiente di esecuzione 

Queste applicazioni vanno eseguite nel nodo **dev** di un ambiente **developer**. 
Vanno però utilizzate più finestre (terminali) diverse. In genere, una per l'applicazione *sentence* e una per il suo client.  

### Esecuzione 

Per l'esecuzione delle diverse versioni dell'applicazione, vedere il file *README* nella cartella di ciascun sottoprogetto. 

In generale, per eseguire una versione dell'applicazione, bisogna avviare individualmente i servizi che la compongono. 
Ciascuna versione dell'applicazione contiene gli script necessari ad avviare l'applicazione. 

**Attenzione**: l'avvio dell'applicazione *sentence* può richiedere da qualche secondo a qualche minuto. 

Complessivamente, l'applicazione *sentence* espone un servizio REST sulla porta **8080** del nodo **dev**, 
ed è possibile ottenere una frase casuale all'indirizzo `localhost:8080`.

In pratica, l'applicazione può essere verificata usando lo script `run-curl-client.sh` oppure `run-curl-client.sh N` per invocare il servizio *N* volte. 
L'applicazione può essere verificata anche usando lo script `run-python-client.sh` del progetto [p-sentence-python-client](p-sentence-python-client/).

Alla fine, l'applicazione può essere arrestata usando lo script `stop-sentence.sh` 
(**da usare con cautela, perché comunque questo script fa il `pkill` di un insieme di processi determinati sulla base di un'espressione regolare**). 

Se bisogna utilizzare anche *Consul*, allora: 

* Consul va avviato separatamente prima dell'applicazione, eseguendo lo script `start-consul.sh`

* Consul va arrestato separatamente dopo dell'applicazione, eseguendo lo script `stop-consul.sh`
