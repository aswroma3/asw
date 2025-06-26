package asw.sentence.sentenceservice.wordclient;

import asw.sentence.sentenceservice.domain.WordClientPort;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service 
public class VerbClientAdapter implements WordClientPort {

	@Autowired 
	private WordRestClient verbRestClient;
	
	public String getWord() {
		return verbRestClient.getWord(); 
	}
	
}

