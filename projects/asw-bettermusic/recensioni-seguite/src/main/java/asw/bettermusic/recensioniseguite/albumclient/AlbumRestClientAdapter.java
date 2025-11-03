package asw.bettermusic.recensioniseguite.albumclient;

import asw.bettermusic.recensioniseguite.domain.*; 

import asw.bettermusic.album.api.rest.*; 

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
public class AlbumRestClientAdapter implements AlbumClientPort {

	@Autowired 
	@Qualifier("loadBalancedWebClient")
    private WebClient loadBalancedWebClient;
	
	public Album getAlbum(Long albumId) {
		Album album = null; 
        Mono<AlbumResponse> response = loadBalancedWebClient
                .get()
				.uri("http://album/album/{albumId}", albumId)
                .retrieve()
                .bodyToMono(AlbumResponse.class);
        try {
            album = toAlbum(response.block());
        } catch (WebClientException e) {
            e.printStackTrace();
        }
		return album; 
	}	

    public Album getAlbum(String titoloAlbum, String artistaAlbum) {
		Album album = null; 
        Mono<AlbumResponse> response = loadBalancedWebClient
                .get()
				.uri(builder -> builder
					.scheme("http")
					.host("album")
					.path("/cercaalbum")
					.queryParam("titolo", titoloAlbum)
					.queryParam("artista", artistaAlbum)
					.build())
                .retrieve()
                .bodyToMono(AlbumResponse.class);
        try {
            album = toAlbum(response.block());
        } catch (WebClientException e) {
            e.printStackTrace();
        }
		return album; 
    }

	public Collection<Album> getAlbumByArtista(String artista) {
		Collection<Album> albums = null; 
        Flux<AlbumResponse> response = loadBalancedWebClient
                .get()
				.uri("http://album/cercaalbum/artista/{artista}", artista)
                .retrieve()
                .bodyToFlux(AlbumResponse.class);
        try {
            albums = toAlbums(response.collectList().block());
        } catch (WebClientException e) {
            e.printStackTrace();
        }
		return albums; 
	}
	
	public Collection<Album> getAlbumByGenere(String genere) {
		Collection<Album> albums = null; 
        Flux<AlbumResponse> response = loadBalancedWebClient
                .get()
				.uri("http://album/cercaalbum/genere/{genere}", genere)
                .retrieve()
                .bodyToFlux(AlbumResponse.class);
        try {
            albums = toAlbums(response.collectList().block());
        } catch (WebClientException e) {
            e.printStackTrace();
        }
		return albums; 
	}

	private Album toAlbum(AlbumResponse response) {
		return new Album(response.getId(), response.getTitolo(), response.getArtista(), response.getGeneri());
	}

	private Collection<Album> toAlbums(Collection<AlbumResponse> response) {
		Collection<Album> albums = 
			response
				.stream()
				.map(a -> toAlbum(a))
				.collect(Collectors.toList());
		return albums; 
	}

}
