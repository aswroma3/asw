package asw.sentence.wordservice.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;
import java.util.logging.Logger; 

@Service
public class WordService {

	@Value("${asw.sentence.wordservice.words}") 
	/* le parole di questo tipo */ 
	private String words;
	
	private Random random = new Random(); 

	public String getWord() {
		/* restituisce una parola a caso tra le parole di questo tipo */ 
		String[] wordArray = words.split(",");
		int randomIndex = random.nextInt(wordArray.length);
		String word = wordArray[randomIndex];
		return word; 
	}
}
