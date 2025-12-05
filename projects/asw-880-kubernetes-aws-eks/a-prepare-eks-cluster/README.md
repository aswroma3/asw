# Preparazione del cluster EKS 

In questa cartella ci sono gli script per la creazione di un cluster Kuebernetes con [Amazon AWS EKS](https://aws.amazon.com/it/eks/).  
Alcune attività però vanno eseguite manualmente. 

Bisogna utilizzare l'ambiente **aws-developer**.  


## Riferimenti 

Guida starter di EKS: https://docs.aws.amazon.com/eks/latest/userguide/getting-started.html


## Attività preliminari da svolgere manualmente 

Avviare l'ambiente *aws-developer* e collegarsi con ssh. 

Nel proprio PC 
* fare il login sul sito [AWS Academy](https://www.awsacademy.com/login/)
* selezionare LMS (in alto a destra) 
* selezionare il corso AWS Academy Learner Lab a cui si è iscritti 
* selezionare Modules 
* selezionare Launch AWS Learner Lab 
* accettare termini e condizioni di uso 
* premere Start Lab (in alto a destra, l'avvio richiede qualche minuto) 
* quando il Lab è partito (pallino verde in alto a sinistra) selezionare AWS Details (in alto a destra) 
  e visualizzare le proprie credenziali (selezionare AWS CLI: Show) 
* copiare e incollare le proprie in un file di testo locale di nome **aws_credentials** in questa cartella


## Preparazione del cluster EKS 

Dopo aver copiato le proprie credenziali nel file **aws_credentials** procedere come segue. 
Attenzione, la creazione del cluster richiede circa 15 minuti. 
* eseguire lo script `configure-aws-access.sh`
* eseguire lo script `configure-eks-cluster.sh`
* eseguire lo script `create-eks-cluster.sh`

Nel cluster appena creato viene anche rilasciato un ingress controller. 
Tuttavia, l'ingress controller, subito dopo il suo rilascio, potrebbe non essere immediatamente raggiungibile in rete.
Attendere circa 1-2 minuti e poi verificare se l'ingress controller è raggiungibile in rete usando lo script `resolve-ingress-controller-address.sh`.

Dopo di che, è possibile rilasciare ed eseguire le proprie applicazioni nel cluster. 

Dopo l'utilizzo, cancellare il cluster per evitare il consumo di risorse.  
* eseguire lo script `delete-eks-cluster.sh`

