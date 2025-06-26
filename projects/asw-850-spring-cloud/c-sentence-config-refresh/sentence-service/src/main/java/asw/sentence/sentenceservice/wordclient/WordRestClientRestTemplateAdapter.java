package asw.sentence.sentenceservice.wordclient;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException; 

@Service 
// @Primary 
public class WordRestClientRestTemplateAdapter implements WordRestClient {

	@Autowired 
	private RestTemplate restTemplate;
	
	public String getWord(String uri) {
		String word = null; 
		try {
			word = restTemplate.getForObject(uri, String.class);
		} catch (RestClientException e) {
            /* eccezione remota */ 
			word = "***"; 
        }
		return word; 
	}	

}
