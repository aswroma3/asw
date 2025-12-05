# Orchestrazione di container con Kubernetes, versione per Amazon AWS EKS (asw-880-kubernetes-aws-eks)

Questo progetto riguarda il rilascio nel servizio [Amazon AWS EKS](https://aws.amazon.com/it/eks/) 
delle applicazioni *hello* e *sentence* mostrate nel progetto [asw-880-kubernetes](../asw-880-kubernetes/). 

L'accesso al servizio AWS avviene mediante l'utilizzo del *Learner Lab* di [AWS Academy](https://aws.amazon.com/it/training/awsacademy/). 


## Ambiente di esecuzione 

Va utilizzato l'ambiente **aws-developer**, che contiene il software utilizzato per il rilascio: 

* *kubectl* ed *Helm* 
* [AWS Command Line Interface](https://docs.aws.amazon.com/cli/)
* [eksctl](https://docs.aws.amazon.com/eks/latest/eksctl/what-is-eksctl.html) 

Prima di poter effettuare il rilascio, è necessario creare il cluster EKS e configurare *AWS CLI* ed *eksctl*, 
come descritto nella cartella [a-prepare-eks-cluster](a-prepare-eks-cluster/) 


## Preparazione del cluster 

Seguire le indicazioni nella cartella [a-prepare-eks-cluster](a-prepare-eks-cluster/) 


## Esecuzione delle applicazioni 

Vedi le indicazioni nelle rispettive cartelle 


## Riferimenti 

Guida starter di EKS: https://docs.aws.amazon.com/eks/latest/userguide/getting-started.html
