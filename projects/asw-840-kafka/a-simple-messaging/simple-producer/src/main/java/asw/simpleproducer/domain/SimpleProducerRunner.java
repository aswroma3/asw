package asw.simpleproducer.domain;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.logging.*;

@Component
public class SimpleProducerRunner implements CommandLineRunner {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private SimpleProducerService simpleProducerService;

	@Value("${asw.kafka.producer.name}")
	private String producerName;

	@Value("${asw.kafka.producer.messages-to-send}")
	private int messagesToSend;

	public void run(String[] args) throws InterruptedException {
		
		for (int i=0; i<messagesToSend || messagesToSend==0; i++) {
			// String message = "Message #" + i; 
			String message = String.format("Message from %1$s #%2$d", producerName, i);
			// logger.info("Sending message: " + message); 
			simpleProducerService.publish(message); 
		}

	}
}
