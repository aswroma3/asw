package asw.sentence.sentenceservice.wordclient;

import asw.sentence.sentenceservice.domain.WordClientPort;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service 
public class SubjectClientAdapter implements WordClientPort {

	@Value("${asw.sentence.sentenceservice.subject.uri}") 
	private String subjectUri;

	@Autowired 
	private WordRestClient wordRestClient;
	
	public String getWord() {
		return wordRestClient.getWord(subjectUri); 
	}
	
}



