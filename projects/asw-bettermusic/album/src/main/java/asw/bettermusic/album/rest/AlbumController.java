package asw.bettermusic.album.rest;

import asw.bettermusic.album.domain.*;

import asw.bettermusic.album.api.rest.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*; 

import java.util.logging.Logger; 

@RestController
public class AlbumController {

	@Autowired 
	private AlbumService albumService; 

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	/* Crea un nuovo album. */ 
	@PostMapping("/album")
	public AlbumResponse createAlbum(@RequestBody CreateAlbumRequest request) {
		String titolo = request.getTitolo();
		String artista = request.getArtista();
		Set<String> generi = request.getGeneri();
		logger.info("REST CALL: createAlbum " + titolo + ", " + artista + ", " + generi); 
		Album album = albumService.createAlbum(titolo, artista, generi);
		AlbumResponse response = toAlbumResponse(album); 
		logger.info("REST CALL: createAlbum " + titolo + ", " + artista + ", " + generi 
		            + " --> " + response); 
		return response; 
	}	

	/* Trova l'album con albumId. */ 
	@GetMapping("/album/{albumId}")
	public AlbumResponse getAlbum(@PathVariable Long albumId) {
		logger.info("REST CALL: getAlbum " + albumId); 
		Album album = albumService.getAlbum(albumId);
		if (album!=null) {
			AlbumResponse response = toAlbumResponse(album); 
			logger.info("REST CALL: getAlbum " + albumId + " --> " + response); 
			return response;
		} else {
			logger.info("REST CALL: getAlbum " + albumId + " ==> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Album not found"
			);
		}
	}

	/* Trova l'album con titolo e artista. */ 
	@GetMapping("/cercaalbum")
	public AlbumResponse getAlbum(@RequestParam String titolo, @RequestParam String artista) {
		logger.info("REST CALL: getAlbum " + titolo + ", " + artista); 
		Album album = albumService.getAlbum(titolo, artista);
		if (album!=null) {
			AlbumResponse response = toAlbumResponse(album); 
			logger.info("REST CALL: getAlbum " + titolo + ", " + artista + " --> " + response); 
			return response;
		} else {
			logger.info("REST CALL: getAlbum " + titolo + ", " + artista + " ==> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Album not found"
			);
		}
	}

	/* Trova tutti gli album. */ 
	@GetMapping("/album")
	public Collection<AlbumResponse> getAlbum() {
		Collection<Album> albums = null; 
		Collection<AlbumResponse> response = null; 
		logger.info("REST CALL: getAlbum"); 
		albums = albumService.getAlbum();
		response = toAlbumsResponse(albums);
		logger.info("REST CALL: getAlbum --> " + response); 
		return response;
	}

	/* Trova tutti gli album di un artista. */ 
	@GetMapping("/cercaalbum/artista/{artista}")
	public Collection<AlbumResponse> getAlbumByArtista(@PathVariable String artista) {
		Collection<Album> albums = null; 
		Collection<AlbumResponse> response = null; 
		logger.info("REST CALL: getAlbumByArtista " + artista); 
		albums = albumService.getAlbumByArtista(artista);
		response = toAlbumsResponse(albums);
		logger.info("REST CALL: getAlbumByArtista " + artista + " --> " + response); 
		return response;
	}

	/* Trova tutti gli album di un genere. */ 
	@GetMapping("/cercaalbum/genere/{genere}")
	public Collection<AlbumResponse> getAlbumByGenere(@PathVariable String genere) {
		Collection<Album> albums = null; 
		Collection<AlbumResponse> response = null; 
		logger.info("REST CALL: getAlbumByGenere " + genere); 
		albums = albumService.getAlbumByGenere(genere);
		response = toAlbumsResponse(albums);
		logger.info("REST CALL: getAlbumByGenere " + genere + " --> " + response); 
		return response;
	}

	/* Converte un album in una risposta album. */ 
	private AlbumResponse toAlbumResponse(Album album) {
		AlbumResponse response = null; 
		if (album!=null) {
			response = new AlbumResponse(album.getId(), album.getTitolo(), 
					album.getArtista(), album.getGeneri());
		} 
		return response; 
	}

	/* Converte una collezione di album in una collezione di risposte album. */ 
	private Collection<AlbumResponse> toAlbumsResponse(Collection<Album> albums) {
		Collection<AlbumResponse> response = 
			albums
				.stream()
				.map(a -> toAlbumResponse(a))
				.collect(Collectors.toList());
		return response; 
	}

}
