package asw.sentence.sentenceservice.wordclient;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException; 

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.ServiceInstance;

@Service 
// @Primary 
public class WordRestClientLoadBalancerClientRestTemplateAdapter implements WordRestClient {

	@Autowired 
	private LoadBalancerClient loadBalancer;

	@Autowired 
	private RestTemplate restTemplate;

	public String getWord(String serviceId) {
		String word = null; 
		URI uri = getWordUri(serviceId); 
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

	private URI getWordUri(String serviceId) {
		URI uri = null; 
		ServiceInstance instance = loadBalancer.choose(serviceId);
		if (instance!=null) {
			uri = instance.getUri();
		}
		return uri; 
	}	
	
}

