package asw.simpleconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* esecuzione: gradle simple-consumer:bootRun */
/* per usare un altro gruppo: ASW_KAFKA_GROUPID=anothergroup gradle simple-consumer:bootRun */
/* per leggere da un altro canale: ASW_KAFKA_CHANNEL_IN=beta gradle simple-consumer:bootRun */
/* per leggere anche messaggi precedenti: SPRING_KAFKA_CONSUMER_AUTO-OFFSET-RESET=earliest ASW_KAFKA_GROUPID=anothergroup gradle simple-consumer:bootRun */
/* per cambiare nome del consumatore: ASW_KAFKA_CONSUMER_NAME=anotherconsumer gradle simple-consumer:bootRun */

@SpringBootApplication
public class SimpleConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleConsumerApplication.class, args);
	}

}

