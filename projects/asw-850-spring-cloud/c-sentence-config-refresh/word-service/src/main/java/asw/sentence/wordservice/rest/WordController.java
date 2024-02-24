package asw.sentence.wordservice.rest;

import asw.sentence.wordservice.domain.WordService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger; 

@RestController
public class WordController {

	@Autowired 
	private WordService wordService;

	private final Logger logger = Logger.getLogger(WordController.class.toString()); 

	@GetMapping("/")
	public String getWord() {
		String word = wordService.getWord(); 
		logger.info("getWord(): " + word);
		return word; 
	}
}
