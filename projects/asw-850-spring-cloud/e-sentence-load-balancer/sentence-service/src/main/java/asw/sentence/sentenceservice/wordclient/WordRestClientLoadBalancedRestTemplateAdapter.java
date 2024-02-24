package asw.sentence.sentenceservice.wordclient;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException; 

@Service 
// @Primary 
public class WordRestClientLoadBalancedRestTemplateAdapter implements WordRestClient {

	@Autowired 
	@Qualifier("loadBalancedRestTemplate")
	private RestTemplate restTemplate;

	public String getWord(String service) {
		String serviceUri = "http://" + service; 
		String word = null; 
		try {
			word = restTemplate.getForObject(serviceUri, String.class);
		} catch (RestClientException e) {
            /* eccezione remota */ 
			word = "***"; 
        } catch (IllegalStateException e) {
            /* nessuna istanza del servizio disponibile */ 
			word = "###"; 
        }
		return word; 
	}	
	
}
