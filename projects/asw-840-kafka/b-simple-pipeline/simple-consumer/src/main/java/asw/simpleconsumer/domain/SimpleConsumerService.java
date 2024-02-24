package asw.simpleconsumer.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Service 
public class SimpleConsumerService {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Value("${asw.kafka.consumer.name}")
	private String consumerName;

    public void onMessage(String message) {
		String logMessage = String.format("RECEIVED MESSAGE [%1$s]: %2$s", consumerName, message);
		logger.info(logMessage);
	}
	
}
