package asw.sentence.sentenceservice.wordclient;

import asw.sentence.sentenceservice.domain.WordClientPort;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service 
public class ObjectClientAdapter implements WordClientPort {

	@Autowired 
	private WordRestClient objectRestClient;
	
	public String getWord() {
		return objectRestClient.getWord(); 
	}
	
}

