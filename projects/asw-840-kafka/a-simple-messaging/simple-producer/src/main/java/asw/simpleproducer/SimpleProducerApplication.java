package asw.simpleproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* esecuzione: gradle simple-producer:bootRun */
/* per inviare messaggi a un altro canale: ASW_KAFKA_CHANNEL_OUT=beta gradle simple-producer:bootRun */
/* per inviare un altro numero di messaggi (0 per infinito): ASW_KAFKA_PRODUCER_MESSAGES_TO_SEND=100 gradle simple-producer:bootRun */
/* per cambiare nome del produttore: ASW_KAFKA_PRODUCER_NAME=anotherproducer gradle simple-producer:bootRun */

@SpringBootApplication
public class SimpleProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleProducerApplication.class, args);
	}

}

