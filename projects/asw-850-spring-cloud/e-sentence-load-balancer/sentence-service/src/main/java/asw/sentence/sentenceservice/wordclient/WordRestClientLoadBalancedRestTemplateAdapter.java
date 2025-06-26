package asw.sentence.sentenceservice.wordclient;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException; 

@Service 
// @Primary 
public class WordRestClientLoadBalancedRestTemplateAdapter implements WordRestClient {

	@Value("${asw.sentence.sentenceservice.serviceIdToUriFormat}") 
	private String serviceIdToUriFormat;

	@Autowired 
	@Qualifier("loadBalancedRestTemplate")
	private RestTemplate restTemplate;

	public String getWord(String serviceId) {
		String uri = getWordUri(serviceId); 
		String word = null; 
		try {
			word = restTemplate.getForObject(uri, String.class);
		} catch (RestClientException e) {
            /* eccezione remota */ 
			word = "***"; 
        } catch (IllegalStateException e) {
            /* nessuna istanza del servizio disponibile */ 
			word = "###"; 
        }
		return word; 
	}	

	private String getWordUri(String serviceId) {
//		String uri = "http://" + service; 
		String uri = String.format(serviceIdToUriFormat, serviceId); 
		return uri; 
	}	

}
