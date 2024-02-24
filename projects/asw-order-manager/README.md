# ORDER-MANAGER

Progetto del corso di Analisi e progettazione del software per l'anno accademico 2023-2024. 


## Descrizione di questo progetto 

Questo progetto contiene il il codice di *OrderManager*, una semplice applicazione a microservizi per la gestione di ordini di prodotti. 

L'applicazione *OrderManager* consente di: 
* creare prodotti e modificare la loro quantità disponibile (ovvero il loro livello di inventario);
* creare ordini relativi a uno o più prodotti; 
* richiedere la validazione di un ordine. 

Un ordine si intende valido se l'ordine esiste, se tutti i prodotti ordinati esistono 
e se inoltre le quantità ordinate di ciascuno dei prodotti ordinati non sono superiori alle quantità disponibili. 


L'applicazione *OrderManager* è composta dai seguenti microservizi: 

* Il servizio *product-service* gestisce i prodotti. 
  Ogni prodotto ha un nome (che lo identifica), la categoria, la quantità disponibile e il prezzo unitario. 
  
  Un esempio di prodotto: 
  * name: Guerra e Pace
  * category: Libro 
  * stockLevel: 3
  * price: 19.99
  
  Operazioni: 
  * `POST /products` crea un nuovo prodotto (dati nome, categoria, quantità disponibile e prezzo unitario, passati nel corpo della richiesta)
  * `GET /products/{name}` trova un prodotto, dato il nome 
  * `GET /products` trova tutti i prodotti 
  * `POST /findproducts/bynames` trova tutti prodotti che hanno il nome compreso in una lista di nomi (la lista di nomi è passata nel corpo della richiesta) 
  * `PATCH /products` aggiorna la quantità disponibile di un prodotto (dati nome e variazione della quantità, passati nel corpo della richiesta) 
  
* Il servizio *order-service* gestisce gli ordini. 
  Ogni ordine ha un id (che lo identifica), il cliente, l'indirizzo, un insieme di righe di ordine (ognuna con nome del prodotto e quantità) e il totale. 
  
  Un esempio di ordine: 
  * id: 2
  * customer: Woody
  * address: Roma 
  * orderItems: 
    * product: Guerra e Pace, quantity: 2
    * product: Anna Karenina, quantity: 1
  * total: 50.97 

  Operazioni: 
  * `POST /orders` crea un nuovo ordine (dati cliente, indirizzo, articoli ordinati e totale, passati nel corpo della richiesta)
  * `GET /orders/{id}` trova un ordine (dato l'id) 
  * `GET /orders` trova tutti gli ordini  
  * `GET /findorders/customer/{customer}` trova tutti gli ordini di un cliente (dato il cliente)  
  * `GET /findorders/product/{product}` trova tutti gli ordini contenenti un certo prodotto (dato il prodotto)  

* Il servizio *order-validation-service* consente di validare un ordine. 
  La convalida di un ordine (l'esito di una validazione) è composta dall'id dell'ordine, alcuni dati dell'ordine (cliente, prodotti ordinati), un indicatore di validità e una motivazione. 
  
  Un esempio di convalida: 
  * id: 6
  * customer: Woody
  * orderItems: 
    * product: Pace e Guerra, quantity: 1
    * product: Anna Karenina, quantity: 10
  * valid: false 
  * motivation: Il prodotto Pace e Guerra non esiste. Il prodotto Anna Karenina non è disponibile nella quantità richiesta. 

  Operazioni: 
  * `GET /ordervalidations/{id}` calcola e restituisce la convalida di un ordine (dato l'id) 

* Il servizio *api-gateway* (esposto sulla porta *8080*) è l'API gateway dell'applicazione che: 
  * espone il servizio *product-service* sul path `/productservice` - ad esempio, `GET /productservice/products`
  * espone il servizio *order-service* sul path `/orderservice` - ad esempio, `GET /orderservice/orders/{id}`
  * espone il servizio *order-validation-service* sul path `/ordervalidationservice` - ad esempio, `GET /ordervalidationservice/ordervalidations/{id}`


## Costruzione 

Per costruire questa applicazione: 

* eseguire il comando `gradle build` 


## Esecuzione 

Per eseguire questa applicazione: 

* avviare *Consul* eseguendo lo script `start-consul.sh` 

* per avviare l'applicazione *OrderManager*, eseguire lo script `run-ordermanager.sh` 

* per inizializzare le basi di dati con alcuni dati di esempio, eseguire gli script `do-init-products.sh` e `do-init-orders.sh` 


Sono anche forniti alcuni script di esempio: 

* lo script `do-get-products.sh` trova tutti i prodotti 

* lo script `do-get-product.sh` trova un prodotto 

* lo script `do-get-orders.sh` trova tutti gli ordini 

* lo script `do-get-ordine.sh` trova un ordine 

* lo script `do-validate-order.sh` convalida un ordine 

Ed inoltre: 

* lo script `do-validate-orders-123.sh` convalida gli ordini 1, 2 e 3

* lo script `do-update-products.sh` aggiorna le quantità disponibili di alcuni ordini 

Ed inoltre ancora: 

* lo script `do-test-1.sh` esegue alcuni test (crea prodotti e ordini e poi esegue le validazioni degli ordini 1, 2 e 3, che sono tutti validi)

* lo script `do-test-2.sh` esegue degli ulteriori test (modifica alcuni prodotti e poi esegue di nuovo le validazioni degli ordini 1, 2 e 3: ora gli ordini 1 e 2 non sono più validi, mentre l'ordine 3 è ancora valido)

* lo script `do-test-3.sh` esegue alcuni ulteriori test (crea un nuovo ordine e poi esegue delle validazioni, che sono tutte non valide)

* nota: questi test possono essere utilmente eseguiti in sequenza, senza eseguire prima nessuno degli altri script  

Alla fine, l'applicazione può essere arrestata usando lo script `terminate-java-processes.sh` (**da usare con cautela!**). 

Inoltre, *Consul* può essere arrestato con lo script `stop-consul.sh`. 


## Descrizione delle attività da svolgere 

Si veda la descrizione del progetto sul sito web del corso di [Architettura dei sistemi software](http://cabibbo.inf.uniroma3.it/asw/).


## Utilizzo di Kafka con Kubernetes

Nel rilascio dell'applicazione *OrderManager* in *Kubernetes*, l'aspetto più difficile è probabilmente l'utilizzo di Kafka.
Nella cartella [kafka-kubernetes](kafka-kubernetes) è possibile trovare alcuni utili suggerimenti in proposito. 
