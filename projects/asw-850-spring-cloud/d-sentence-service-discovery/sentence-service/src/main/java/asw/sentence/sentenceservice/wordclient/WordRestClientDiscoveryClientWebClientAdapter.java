package asw.sentence.sentenceservice.wordclient;

import java.net.URI;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service 
@Primary 
public class WordRestClientDiscoveryClientWebClientAdapter implements WordRestClient {

	@Autowired 
	private DiscoveryClient discoveryClient;

    @Autowired
	private WebClient webClient;

	private Random random = new Random(); 

	public String getWord(String service) {
		String word = null; 
		URI uri = getWordUri(service); 
		if (uri!=null) {
			word = webClientGet(uri); 
		} else {
			/* nessuna istanza del servizio disponibile */ 
			word = "###"; 
		}
		return word; 
	}	

	private String webClientGet(URI uri) {
		String word = null; 
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
