package asw.sentence.sentenceservice.rest;

import asw.sentence.sentenceservice.domain.SentenceService; 

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger; 

@RestController
public class SentenceController {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired 
	private SentenceService sentenceService;

	@GetMapping("/")
	public String getSentence() {
		String sentence = sentenceService.getSentence(); 
		logger.info("getSentence(): " + sentence);
		return sentence; 
	}
	
}
