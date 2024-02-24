package asw.simplefilter.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Service 
public class SimpleFilterService {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private SimpleMessagePublisherPort simpleMessagePublisher;

	@Value("${asw.kafka.filter.name}")
	private String filterName;

    public void filter(String inMessage) {
//		String outMessage = String.format("*** [%2$s] %1$s ***", inMessage, filterName);
		String outMessage = String.format("*** %1$s [Filtered by %2$s] ***", inMessage, filterName);
		logger.info("FILTERING MESSAGE: " + inMessage + " TO: " + outMessage);
		simpleMessagePublisher.publish(outMessage); 
	}
	
}
