package asw.sentence.sentenceservice.wordclient;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service("objectRestClient") 
public class ObjectRestClientCircuitBreakerRetryWebClientAdapter implements WordRestClient {

	@Autowired 
	@Qualifier("loadBalancedWebClient")
    private WebClient webClient;
	
    @CircuitBreaker(name = "objectClientCircuitBreaker", fallbackMethod = "getFallbackWord")
    @Retry(name = "wordClientRetry")
	public String getWord(String service) {
		String serviceUri = "http://" + service; 
        Mono<String> response = webClient
                .get()
				.uri(serviceUri)
                .retrieve()
                .bodyToMono(String.class);
        return response.block();
	}	

	private String getFallbackWord(Exception e) {
		String fallbackWord = "* fallback word *"; 
		return fallbackWord; 
	}

}
