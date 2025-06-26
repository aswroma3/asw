package asw.sentence.sentenceservice.wordclient;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service 
@Primary 
public class WordRestClientLoadBalancedWebClientAdapter implements WordRestClient {

	@Value("${asw.sentence.sentenceservice.serviceIdToUriFormat}") 
	private String serviceIdToUriFormat;

	@Autowired 
	@Qualifier("loadBalancedWebClient")
    private WebClient webClient;
	
	public String getWord(String serviceId) {
		String word = null; 
		String uri = getWordUri(serviceId); 
        Mono<String> response = webClient
                .get()
				.uri(uri)
                .retrieve()
                .bodyToMono(String.class);
        try {
            word = response.block();
        } catch (WebClientException e) {
            /* eccezione remota */ 
			word = "***";
        }
		return word; 
	}	

	private String getWordUri(String serviceId) {
//		String uri = "http://" + service; 
		String uri = String.format(serviceIdToUriFormat, serviceId); 
		return uri; 
	}	

}
