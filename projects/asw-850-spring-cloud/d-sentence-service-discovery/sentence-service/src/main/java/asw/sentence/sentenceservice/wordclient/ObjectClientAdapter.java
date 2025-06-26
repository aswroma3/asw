package asw.sentence.sentenceservice.wordclient;

import asw.sentence.sentenceservice.domain.WordClientPort;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service 
public class ObjectClientAdapter implements WordClientPort {

	@Value("${asw.sentence.sentenceservice.object.serviceId}") 
	private String serviceId;

	@Autowired 
	private WordRestClient wordRestClient;
	
	public String getWord() {
		return wordRestClient.getWord(serviceId); 
	}
	
}



