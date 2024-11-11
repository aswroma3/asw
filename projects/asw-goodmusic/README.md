# GOODMUSIC

Progetto del corso di Analisi e progettazione del software per l'anno accademico 2024-2025. 


## Descrizione di questo progetto 

Questo progetto contiene il il codice di *GoodMusic*, 
un semplice social network per la condivisione di recensioni di album musicali.  
Gli utenti del sistema possono scrivere e pubblicare delle recensioni. 
Possono poi seguire le recensioni scritte da specifici recensori e quelle degli album realizzati da specifici artisti.  
Quando un utente accede alla pagina delle recensioni che segue, gli vengono mostrate le recensioni dei recensori e degli artisti che segue. 

L'applicazione *GoodMusic* è composta dai seguenti microservizi: 

* Il servizio *recensioni* gestisce le recensioni. 
  Ogni recensione è scritta da un recensore, si riferisce ad un album realizzato da un artista, con un genere musicale, 
  e contiene il testo della recensione (che può essere molto lungo) ed un suo sunto (di solito breve). 
  
  Un esempio di recensione: 
  * recensore: Woody
  * album: The Dark Side of the Moon
  * artista: Pink Floyd
  * genere: Rock
  * testo: Bla bla bla...
  * sunto: Il lato buio dell'animo umano
  
  Una recensione in formato breve è una recensione che non contiene il testo completo della recensione, ma solo il suo sunto. 
  Per motivi di prestazioni, le operazioni che restituiscono più recensioni, le restituiscono in formato breve. 
  
  Operazioni: 
  * `POST /recensioni` aggiunge una nuova recensione (dati recensore, album, artista, testo e sunto della recensione)
  * `GET /recensioni/{id}` trova una recensione, dato l'id (la recensione contiene il testo completo della recensione) 
  * `GET /recensioni` trova tutte le recensioni (in formato breve) 
  * `GET /cercarecensioni/album/{album}` trova tutte le recensioni (in formato breve) di un certo album
  * `GET /cercarecensioni/recensore/{recensore}` trova tutte le recensioni (in formato breve) di un certo recensore
  * `GET /cercarecensioni/recensori/{insieme-di-recensori}` trova tutte le recensioni (in formato breve) di un insieme di recensori 
  * `GET /cercarecensioni/artista/{artista}` trova tutte le recensioni (in formato breve) degli album di un certo artista 
  * `GET /cercarecensioni/artisti/{insieme-di-artisti}` trova tutte le recensioni (in formato breve) degli album di un insieme di artisti 
  * `GET /cercarecensioni/genere/{genere}` trova tutte le recensioni (in formato breve) degli album di un certo genere musicale 
  * `GET /cercarecensioni/generi/{insieme-di-generi}` trova tutte le recensioni (in formato breve) degli album di un insieme di generi 
  
* Il servizio *connessioni* gestisce le connessioni degli utenti con le recensioni che seguono, 
  e più precisamente con gli artisti, con i recensori e con i generi musicali che essi seguono. 

  Le connessioni sono delle terne *utente-seguito-ruolo*, in cui l'*utente* è chi segue, 
  *seguito* è chi o che cosa è seguito (un artista oppure uno che scrive recensioni oppure un genere musicale) 
  e *ruolo* rappresenta il ruolo del seguito, e può essere *ARTISTA* oppure *RECENSORE* oppure *GENERE*. 
  Per esempio, la terna *Alice-Pink Floyd-ARTISTA* indica che *Alice* è interessata a seguire le recensioni degli album dei *Pink Floyd*. 
  Invece, la terna *Bob-OndaRock-RECENSORE* indica che *Bob* è interessato a seguire le recensioni scritte da *OndaRock*. 
  Inoltre, la terna *Carlo-Pop-GENERE* indica che *Carlo* è interessato a seguire le recensioni sugli album del genere *Pop*. 

  Operazioni: 
  * `POST /connessioni` aggiunge una nuova connessione utente-seguito-ruolo (dati utente, seguito e ruolo)
  * `GET /connessioni` trova tutte le connessioni 
  * `GET /connessioni/{utente}` trova tutte le connessioni di un certo utente
  * `GET /connessioni/{utente}/{ruolo}` trova tutte le connessioni di un certo utente relative a un certo ruolo
  * `DELETE /connessioni` cancella una connessione utente-seguito-ruolo (dati utente, seguito e ruolo)

* Il servizio *recensioni-seguite* consente a un utente di trovare le recensioni degli artisti e dei recensori e dei generi musicali che segue. 

  Operazioni: 
  * `GET /recensioniseguite/{utente}` trova tutti le recensioni seguite da un certo utente, 
    ovvero le recensioni di artisti di album e di recensori e di generi musicali seguiti da quell'utente
	(le recensioni sono in formato breve) 
  
* Il servizio *api-gateway* (esposto sulla porta *8080*) è l'API gateway dell'applicazione che: 
  * espone il servizio *recensioni* sul path `/recensioni` - ad esempio, `GET /recensioni/recensioni`
  * espone il servizio *connessioni* sul path `/connessioni` - ad esempio, `GET /connessioni/connessioni/{utente}`
  * espone il servizio *recensioni-seguite* sul path `/recensioni-seguite` - ad esempio, `GET /recensioni-seguite/recensioniseguite/{utente}`


## Esecuzione 

Per eseguire questo progetto: 

* per avviare *Consul* e l'applicazione *GoodMusic*, eseguire lo script `start-goodmusic.sh` 

* per inizializzare le basi di dati con dei dati di esempio, eseguire gli script `do-init-recensioni.sh` e `do-init-connessioni.sh` 

Sono anche forniti alcuni script di esempio: 

* lo script `run-curl-client.sh` esegue un insieme di interrogazioni di esempio 

* lo script `do-get-recensioni.sh` trova tutte le recensioni 

* lo script `do-get-recensione.sh` trova una recensione 

* lo script `do-get-recensioni-per-album.sh` trova tutte le recensioni relative a un certo album 

* lo script `do-get-recensioni-per-artista.sh` trova tutte le recensioni relative a un certo artista 

* lo script `do-get-recensioni-per-artisti.sh` trova tutte le recensioni di un insieme di artisti  

* lo script `do-get-recensioni-per-genere.sh` trova tutte le recensioni relative a un certo genere 

* lo script `do-get-recensioni-per-generi.sh` trova tutte le recensioni di un insieme di generi  

* lo script `do-get-recensioni-per-recensore.sh` trova tutte le recensioni di un certo recensore 

* lo script `do-get-recensioni-per-recensori.sh` trova tutte le recensioni di un insieme di recensori  

* lo script `do-get-connessioni.sh` trova tutte le connessioni 

* lo script `do-get-recensioni-seguite.sh` trova tutte le recensioni seguite da un certo utente 

Inoltre: 

* gli script `do-update-recensioni.sh` e `do-update-connessioni.sh` eseguono degli aggiornamenti delle basi di dati, 
  utili per verificare la correttezza dell'applicazione  

Alla fine, l'applicazione può essere arrestata, insieme a *Consul*, usando lo script `stop-goodmusic.sh`. 


## Test minimale 

Con riferimento agli script forniti: 

* eseguire gli script `do-init-recensioni.sh` e `do-init-connessioni.sh` 

* eseguire lo script `run-curl-client.sh`: 
  le recensioni seguite da Alice, Bob e Carlo (ultime risposte fornite da questo script) dovrebbero essere rispettivamente 5, 5 e 3 

* eseguire lo script `do-update-recensioni.sh`

* eseguire di nuovo lo script `run-curl-client.sh`: 
  ora le recensioni seguite da Alice, Bob e Carlo dovrebbero essere rispettivamente 6, 7 e 4 

* eseguire lo script `do-update-connessioni.sh`

* eseguire di nuovo lo script `run-curl-client.sh`: 
  ora le recensioni seguite da Alice, Bob e Carlo dovrebbero essere rispettivamente 7, 8 e 1 


## Descrizione delle attività da svolgere 

Si veda la descrizione del progetto sul sito web del corso di [Architettura dei sistemi software](http://cabibbo.dia.uniroma3.it/asw/).


## Utilizzo di Kafka con Docker e Kubernetes

Ecco alcune [note sull'Utilizzo di Kafka con Docker e Kubernetes](kafka/).
