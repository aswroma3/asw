package asw.sentence.sentenceservice.rest;

import asw.sentence.sentenceservice.domain.SentenceService; 

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant; 
import java.time.Duration; 

import java.util.logging.Logger; 

@RestController
public class SentenceController {

	@Autowired 
	private SentenceService sentenceService;

	@Value("${asw.sentence.sentenceservice.delay:0}") 
	/* ritardo da introdurre */ 
	private int delay;

	@Value("${asw.sentence.sentenceservice.instancename:}") 
	/* nome dell'istanza */ 
	private String instanceName;

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@GetMapping("/")
	public String getSentence() {
		Instant start = Instant.now();
		String sentence = sentenceService.getSentence(); 
		if (delay>0) {
			this.sleep(delay); 
		}
		Duration duration = Duration.between(start, Instant.now()); 
		if (instanceName!=null && instanceName.length()>0) {
			sentence += " [" + instanceName + ":" + delay + ":" + duration.toMillis() + "]";
		}
		logger.info("getSentence(): " + sentence);
		return sentence; 
	}

	/* Introduce un ritardo di delay millisecondi. */
	private void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
	}
	
}
