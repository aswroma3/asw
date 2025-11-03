package asw.bettermusic.recensioniseguite.connessioni;

import asw.bettermusic.recensioniseguite.domain.*; 

import asw.bettermusic.connessioni.api.rest.*; 

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*; 
import java.util.stream.*; 

@Service 
@Primary 
public class ConnessioniRestClientAdapter implements ConnessioniClientPort {

	@Autowired 
	@Qualifier("loadBalancedWebClient")
    private WebClient loadBalancedWebClient;
	
	public Collection<Connessione> getConnessioniByUtente(String utente) {
		Collection<Connessione> connessioni = null; 
        Flux<ConnessioneResponse> response = loadBalancedWebClient
                .get()
				.uri("http://connessioni/connessioni/{utente}", utente)
                .retrieve()
                .bodyToFlux(ConnessioneResponse.class);
        try {
            connessioni = toConnessioni(response.collectList().block());
        } catch (WebClientException e) {
            e.printStackTrace();
        }
		return connessioni; 
	}	

	public Collection<Connessione> getConnessioniByUtenteAndRuolo(String utente, String ruolo) {
		Collection<Connessione> connessioni = null; 
        Flux<ConnessioneResponse> response = loadBalancedWebClient
                .get()
				.uri("http://connessioni/connessioni/{utente}/{ruolo}", utente, ruolo)
                .retrieve()
                .bodyToFlux(ConnessioneResponse.class);
        try {
            connessioni = toConnessioni(response.collectList().block());
        } catch (WebClientException e) {
            e.printStackTrace();
        }
		return connessioni; 
	}	

	private Connessione toConnessione(ConnessioneResponse response) {
		return new Connessione(response.getId(), response.getUtente(), response.getSeguito(), response.getRuolo());
	}

	private Collection<Connessione> toConnessioni(Collection<ConnessioneResponse> response) {
		Collection<Connessione> connessioni = 
			response
				.stream()
				.map(r -> toConnessione(r))
				.collect(Collectors.toList());
		return connessioni; 
	}

}
