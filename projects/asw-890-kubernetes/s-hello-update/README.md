# Rolling update con Kubernetes

Questo sottoprogetto esemplifica l'esecuzione di rolling update con **Kubernetes**. 


## Ambiente di esecuzione 

Questa applicazione va eseguita nell'ambiente [kube-cluster](../../environments/kube-cluster/), sul nodo **kube-dev**. 
Vanno però utilizzate più finestre (terminali) diverse. In genere, una per l'applicazione e una per il suo client.  


## Esecuzione 

Sul nodo **kube-dev**, si proceda come segue: 

* per avviare l'applicazione **hello**, nella versione **1.0**, eseguire lo script `a-deploy-hello-v1.sh` in una prima finestra

* in una seconda finestra, eseguire lo script `run-curl-client-ingress.sh 1000` (oppure `run-curl-client-nodeport.sh 1000`) 
  che invoca il servizio **hello** per 1000 volte e consente di monitorare la versione attualmente in esecuzione dell'applicazione 
 
* per aggiornare l'applicazione **hello** alla versione **2.0**, eseguire lo script `b-update-hello-v2.sh` nella prima finestra 

* nella seconda finestra, verificare la transizione dalla versione **1.0** alla versione **2.0** per tutte le repliche del servizio  
  (riavviare lo script se è terminato)

* per aggiornare l'applicazione **hello** alla versione **3.0**, eseguire lo script `c-update-hello-v3.sh` nella prima finestra 

* nella seconda finestra, verificare la transizione dalla versione **2.0** alla versione **3.0** per tutte le repliche del servizio  
  (riavviare lo script se è terminato)

* la versione **3.0** dell'applicazione è però lenta e problematica; 
  per effettuare il rollback alla versione precedente dell'applicazione, eseguire lo script `d-rollback-hello` nella prima finestra 

* nella seconda finestra, verificare la transizione dalla versione **3.0** alla versione **2.0** per tutte le repliche del servizio  
  (riavviare lo script se è terminato)

* per arrestare l'applicazione **hello**, eseguire lo script `undeploy-hello.sh` 

