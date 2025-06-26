package asw.sentence.sentenceservice.wordclient;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service("verbRestClient") 
public class VerbRestClientCircuitBreakerRetryWebClientAdapter implements WordRestClient {

	@Value("${asw.sentence.sentenceservice.verb.serviceId}") 
	private String serviceId;
	
	@Value("${asw.sentence.sentenceservice.serviceIdToUriFormat}") 
	private String serviceIdToUriFormat;

	@Autowired 
	@Qualifier("loadBalancedWebClient")
    private WebClient webClient;
	
    @CircuitBreaker(name = "verbClientCircuitBreaker", fallbackMethod = "getFallbackWord")
    @Retry(name = "wordClientRetry")
	public String getWord() {
		String uri = getWordUri(serviceId); 
        Mono<String> response = webClient
                .get()
				.uri(uri)
                .retrieve()
                .bodyToMono(String.class);
        return response.block();
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
