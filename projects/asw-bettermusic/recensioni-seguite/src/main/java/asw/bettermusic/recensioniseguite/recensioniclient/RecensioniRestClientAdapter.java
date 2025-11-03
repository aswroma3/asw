package asw.bettermusic.recensioniseguite.enigmi;

import asw.bettermusic.recensioniseguite.domain.*; 

import asw.bettermusic.recensioni.api.rest.*; 

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
public class RecensioniRestClientAdapter implements RecensioniClientPort {

	@Autowired 
	@Qualifier("loadBalancedWebClient")
    private WebClient loadBalancedWebClient;
	
	public Collection<Recensione> getRecensioniByRecensore(String recensore) {
		Collection<Recensione> recensioni = null; 
		Flux<RecensioneBreveResponse> response = loadBalancedWebClient
				.get()
				.uri("http://recensioni/cercarecensioni/recensore/{recensore}", recensore)
				.retrieve()
				.bodyToFlux(RecensioneBreveResponse.class);
		try {
			recensioni = toRecensioni(response.collectList().block());
		} catch (WebClientException e) {
			e.printStackTrace();
		}
		return recensioni; 
	}

	public Collection<Recensione> getRecensioniByIdAlbum(Long idAlbum) {
		Collection<Recensione> recensioni = null; 
		Flux<RecensioneBreveResponse> response = loadBalancedWebClient
				.get()
				.uri("http://recensioni/cercarecensioni/album/{idAlbum}", idAlbum)
				.retrieve()
				.bodyToFlux(RecensioneBreveResponse.class);
		try {
			recensioni = toRecensioni(response.collectList().block());
		} catch (WebClientException e) {
			e.printStackTrace();
		}
		return recensioni; 
	}

	private Recensione toRecensione(RecensioneBreveResponse response) {
		return new Recensione(response.getId(), response.getRecensore(), 
				response.getIdAlbum(), response.getSunto());
	}

	private Collection<Recensione> toRecensioni(Collection<RecensioneBreveResponse> response) {
		Collection<Recensione> recensioni = 
			response
				.stream()
				.map(r -> toRecensione(r))
				.collect(Collectors.toList());
		return recensioni; 
	}

}
