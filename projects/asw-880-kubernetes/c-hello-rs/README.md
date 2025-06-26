# Hello - script per il rilascio con Kubernetes 

### Tipo di rilascio 

Replica set  

### Ambiente di esecuzione 

Nodi **kube-dev** e **kube-1** di un ambiente **kube-cluster**. 

### Esecuzione 

* Avvio: eseguire lo script `deploy-hello.sh` dal nodo **kube-dev**

* Test: eseguire lo script `run-curl-client-rs-dal-cluster.sh` dal nodo **kube-1**

* Arresto: eseguire lo script `undeploy-hello.sh` dal nodo **kube-dev**


