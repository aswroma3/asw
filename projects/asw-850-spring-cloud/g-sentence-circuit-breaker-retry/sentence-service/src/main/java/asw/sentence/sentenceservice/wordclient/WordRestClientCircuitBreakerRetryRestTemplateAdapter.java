package asw.sentence.sentenceservice.wordclient;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service("wordRestClientCircuitBreakerRetryRestTemplate") 
// @Primary 
public class WordRestClientCircuitBreakerRetryRestTemplateAdapter implements WordRestClient {

	@Value("${asw.sentence.sentenceservice.serviceIdToUriFormat}") 
	private String serviceIdToUriFormat;

	@Autowired 
	@Qualifier("loadBalancedRestTemplate")
	private RestTemplate restTemplate;

    @CircuitBreaker(name = "wordClientCircuitBreaker", fallbackMethod = "getFallbackWord")
    @Retry(name = "wordClientRetry")
	// oppure 
    // @CircuitBreaker(name = "wordClientCircuitBreaker", fallbackMethod = "getFallbackWord")
	// oppure 
    // @Retry(name = "wordClientRetry", fallbackMethod = "getFallbackWord")
	public String getWord(String serviceId) {
		String uri = getWordUri(serviceId); 
		return restTemplate.getForObject(uri, String.class);
	}	

	private String getFallbackWord(Exception e) {
		String fallbackWord = "* fallback word *"; 
		return fallbackWord; 
	}

	private String getWordUri(String serviceId) {
//		String uri = "http://" + service; 
		String uri = String.format(serviceIdToUriFormat, serviceId); 
		return uri; 
	}	
	
}
