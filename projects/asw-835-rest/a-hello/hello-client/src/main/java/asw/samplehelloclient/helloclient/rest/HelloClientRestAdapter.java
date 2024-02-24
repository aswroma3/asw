package asw.samplehelloclient.helloclient.rest;

import asw.samplehelloclient.domain.HelloClientPort;

import java.util.logging.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@Service
public class HelloClientRestAdapter implements HelloClientPort {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Value("${asw.helloservice.rest.uri}")
    private String helloServiceUri;

    private WebClient webClient;

    public HelloClientRestAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(helloServiceUri).build();
    }

	/* Invoca sayHello. */ 
    public String sayHello(String name) {
        logger.info("sayHello(" + name + ") [WEBCLIENT]");
		String greeting = null; 
        Mono<String> response = webClient
                .get()
				.uri(helloServiceUri + "/hello/{name}", name)
                .retrieve()
                .bodyToMono(String.class);
        try {
            greeting = response.block();
        } catch (WebClientException e) {
            logger.info("sayHello(" + name + ") [WEBCLIENT] exception: " + e.getMessage());
        }
		logger.info("sayHello(" + name + ") [WEBCLIENT]: " + greeting);
		return greeting; 
    }

}
