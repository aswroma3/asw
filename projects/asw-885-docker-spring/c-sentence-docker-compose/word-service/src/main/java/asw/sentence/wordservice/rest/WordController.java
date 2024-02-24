package asw.sentence.wordservice.rest;

import asw.sentence.wordservice.domain.WordService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.server.ResponseStatusException; 
import org.springframework.http.HttpStatus;

import java.util.logging.Logger; 
import java.util.Random;

@RestController
public class WordController {

	@Autowired 
	private WordService wordService;

	@Value("${asw.sentence.wordservice.delay:0}") 
	/* ritardo da introdurre */ 
	private int delay;

	@Value("${asw.sentence.wordservice.instancename:}") 
	/* nome dell'istanza */ 
	private String instanceName;

	@Value("${asw.sentence.wordservice.failurerate:0}") 
	/* tasso percentuale di fallimento, tra 0 e 100 */ 
	private int failureRate;

	private Random random = new Random(); 

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@GetMapping("/")
	public String getWord() {
		String word = wordService.getWord(); 
		boolean failure = false; 
		if (delay>0) {
			this.sleep(delay); 
		}
		if (failureRate>0) {
			int randomIndex = random.nextInt(100);
			failure = (randomIndex<failureRate); 
		}
		if (failure) {
			String errorMessage = "Word service is temporarily unavailable";
			logger.info("getWord() " + getName() + " ERROR: " + errorMessage);
			throw new ResponseStatusException(
				HttpStatus.SERVICE_UNAVAILABLE, errorMessage);
		}
		logger.info("getWord(): " + word + " " + getName());
		return word; 
	}
	
	private String getName() {
		String name = ""; 
		if (instanceName!=null && instanceName.length()>0) {
			name = "(" + instanceName + ":" + delay + ":" + failureRate + ")";
		}
		return name; 
	}

	/* Introduce un ritardo di delay millisecondi. */
	private void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {}
	}

}
