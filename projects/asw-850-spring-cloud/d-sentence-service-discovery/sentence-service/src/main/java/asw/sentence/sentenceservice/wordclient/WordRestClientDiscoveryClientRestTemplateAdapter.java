package asw.sentence.sentenceservice.wordclient;

import java.net.URI;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException; 

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;

@Service 
// @Primary 
public class WordRestClientDiscoveryClientRestTemplateAdapter implements WordRestClient {

	@Autowired 
	private DiscoveryClient discoveryClient;

	@Autowired 
	private RestTemplate restTemplate;
	
	private Random random = new Random(); 

	public String getWord(String service) {
		String word = null; 
		URI uri = getWordUri(service); 
		if (uri!=null) {
			word = restTemplateGet(uri); 
		} else {
			/* nessuna istanza del servizio disponibile */ 
			word = "###"; 
		}
		return word; 
	}

	private String restTemplateGet(URI uri) {
		String word = null; 
		try {
			word = restTemplate.getForObject(uri, String.class);
		} catch (RestClientException e) {
            /* eccezione remota */ 
			word = "***"; 
        }
		return word; 
	}

	private URI getWordUri(String service) {
		URI uri = null; 
		List<ServiceInstance> list = discoveryClient.getInstances(service);
		if (list!=null && list.size()>0) {
//			uri = list.get(0).getUri();
			int randomIndex = random.nextInt(list.size());
			uri = list.get(randomIndex).getUri();
		}
		return uri; 
	}	
	
}
