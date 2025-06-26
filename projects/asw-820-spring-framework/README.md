# Introduzione a Spring (asw-820)

Questo progetto contiene alcune semplici applicazioni 
per introdurre l'uso del [framework Spring](https://spring.io/projects/spring-framework) 
ed esemplificare l'iniezione delle dipendenze e le configurazioni con Spring:  

* **a-show-xml-configuration** esemplifica la configurazione basata su file XML 

* **b-show-java-configuration** esemplifica la configurazione basata su esemplifica la configurazione basata su file XML

* **c-show-autowiring** esemplifica il component scanning e l'autowiring

Le diverse applicazioni hanno una struttura simile, 
e la loro costruzione ed esecuzione è descritta qui di seguito. 

### Build  

Per la costruzione di ciascuna applicazione, vedere le istruzioni 
descritte nella sezione [projects/](../). 

In pratica, per compilare e assemblare ciascuna applicazione, bisogna 

1. posizionarsi nella cartella principale dell'applicazione di interesse - ad esempio `~/projects/asw-820-spring-framework/a-show-xml-configuration`

2. per compilare e assemblare l'applicazione, usare il comando `gradle build` 

### Componenti eseguibili 

Ciascuna di queste applicazioni è composta da un unico componente eseguibile. 

### Ambiente di esecuzione 

Ciascuna di queste applicazioni può essere eseguita direttamente sul proprio PC,
oppure nel nodo **dev** di un ambiente **developer**. 

### Esecuzione 

Per eseguire un'applicazione: 

1. posizionarsi nella cartella principale dell'applicazione di interesse - ad esempio `~/projects/asw-820-spring-framework/a-show-xml-configuration`

2. eseguire il comando `gradle run` 

