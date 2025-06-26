package asw.sentence.sentenceservice.wordclient;

import asw.sentence.sentenceservice.domain.WordClientPort;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service 
public class SubjectClientAdapter implements WordClientPort {

	@Autowired 
	private WordRestClient subjectRestClient;
	
	public String getWord() {
		return subjectRestClient.getWord(); 
	}
	
}

