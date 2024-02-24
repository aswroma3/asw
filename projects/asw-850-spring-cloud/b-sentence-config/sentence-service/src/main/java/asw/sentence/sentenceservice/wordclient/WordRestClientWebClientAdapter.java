package asw.sentence.sentenceservice.wordclient;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service 
@Primary 
public class WordRestClientWebClientAdapter implements WordRestClient {

	@Autowired
    private WebClient webClient;
	
	public String getWord(String wordUri) {
		String word = null; 
        Mono<String> response = webClient
                .get()
				.uri(wordUri)
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

}
