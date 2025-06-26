# Orchestrazione di container con Kubernetes (asw-880)

Questo progetto contiene alcune applicazioni che esemplificano il rilascio di applicazioni a servizi mediante l'orchestrazione di container con **Kubernetes**. 

In particolare, le applicazioni sono in genere delle varianti delle applicazioni **hello** descritta nel progetto [asw-825-spring-boot/](../asw-825-spring-boot/) e 
dell'applicazione distribuita **sentence** descritte nei progetti [asw-850-spring-cloud/](../asw-850-spring-cloud/) e [asw-875-docker-spring/](../asw-875-docker-spring/). 

* [a-hello-kube-dockerhub](a-hello-kube-dockerhub/): codice sorgente per l'applicazione **hello** (le immagini dei container sono già disponibili su *Docker Hub*)

* [b-hello-pod](b-hello-pod/): esemplifica l'uso di una risorsa *pod* 

* [c-hello-rs](c-hello-rs/): esemplifica l'uso di una risorsa *replica set* 

* [d-hello-deployment](d-hello-deployment/): esemplifica l'uso di una risorsa *deployment* 

* [e-hello-service-clusterip](e-hello-service-clusterip/) e [f-hello-service-nodeport](f-hello-service-nodeport/): esemplificano l'uso di risorse *service* 

* [g-hello-ingress](g-hello-ingress/): esemplifica l'uso di una risorsa *ingress* 

* [h-hello-namespace](h-hello-namespace/): esemplifica l'uso di una risorsa *namespace* 

* [i-hello-application](i-hello-application/): l'applicazione **hello** per Kubernetes completa 

* [l-sentence-kube-dockerhub](l-sentence-kube-dockerhub/): codice sorgente per l'applicazione **sentence** (le immagini dei container sono già disponibili su *Docker Hub*)

* [m-sentence-application](m-sentence-application/): la specifica dell'applicazione **sentence** per Kubernetes 

* [n-sentence-application-helm](n-sentence-application-helm/): la specifica dell'applicazione **sentence** per Kubernetes basata su Helm 

* [o-sentence-application-ingress-path-rewriting](o-sentence-application-ingress-path-rewriting/): esperimenti con l'ingress controller ed il path rewriting 

* [p-sentence-kube-dns-dockerhub](p-sentence-kube-dns-dockerhub/): codice sorgente per l'applicazione **sentence** basata su DNS anziché su service discovery (le immagini dei container sono già disponibili su *Docker Hub*)

* [q-sentence-kube-dns-application](q-sentence-kube-dns-application/): la specifica dell'applicazione **sentence** basata su DNS anziché su service discovery per Kubernetes 

* [r-hello-update-kube-dockerhub](r-hello-update-kube-dockerhub/): codice sorgente per tre diverse versione dell'applicazione **hello-update** (le immagini dei container sono già disponibili su *Docker Hub*)

* [s-hello-update](s-hello-update/): esperimenti che esemplificano l'esecuzione di rolling update con Kubernetes con riferimento all'applicazione **hello-update** 


## Ambiente di esecuzione 

Queste applicazioni vanno eseguite nel nodo **kube-dev** di un ambiente **kube-cluster**. 

In alcuni casi preliminari o intermedi, il client può essere eseguito solo dai nodi **kube-1**, **kube-2** e **kube-3** del cluster.
Può essere necessario utilizzare più finestre (terminali) diverse. In genere, una per l'applicazione e una per il suo client.  
