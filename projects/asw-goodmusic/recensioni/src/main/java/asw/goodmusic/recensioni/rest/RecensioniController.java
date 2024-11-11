package asw.goodmusic.recensioni.rest;

import asw.goodmusic.recensioni.domain.*;

import asw.goodmusic.recensioni.api.rest.*;

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
public class RecensioniController {

	@Autowired 
	private RecensioniService recensioniService; 

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	/* Crea una nuova recensione. */ 
	@PostMapping("/recensioni")
	public RecensioneResponse createRecensione(@RequestBody CreateRecensioneRequest request) {
		String recensore = request.getRecensore();
		String album = request.getAlbum();
		String artista = request.getArtista();
		String genere = request.getGenere();
		String testo = request.getTesto();
		String sunto = request.getSunto();
		logger.info("REST CALL: createRecensione " + recensore + ", " + album + ", " + artista + ", " + genere + ", " + testo + ", " + sunto); 
		Recensione recensione = recensioniService.createRecensione(recensore, album, artista, genere, testo, sunto);
		RecensioneResponse response = toRecensioneResponse(recensione); 
		logger.info("REST CALL: createRecensione " + recensore + ", " + album + ", " + artista + ", " + genere + ", " + testo + ", " + sunto 
		            + " --> " + response); 
		return response; 
	}	

	/* Trova la recensione con recensioneId. */ 
	@GetMapping("/recensioni/{recensioneId}")
	public RecensioneResponse getRecensione(@PathVariable Long recensioneId) {
		logger.info("REST CALL: getRecensione " + recensioneId); 
		Recensione recensione = recensioniService.getRecensione(recensioneId);
		if (recensione!=null) {
			RecensioneResponse response = toRecensioneResponse(recensione); 
			logger.info("REST CALL: getRecensione " + recensioneId + " --> " + response); 
			return response;
		} else {
			logger.info("REST CALL: getRecensione " + recensioneId + " ==> NOT FOUND"); 
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Recensione not found"
			);
		}
	}

	/* Trova tutte le recensioni (in formato breve). */ 
	@GetMapping("/recensioni")
	public Collection<RecensioneBreveResponse> getRecensioni() {
		Collection<Recensione> recensioni = null; 
		Collection<RecensioneBreveResponse> response = null; 
		logger.info("REST CALL: getRecensioni"); 
		recensioni = recensioniService.getRecensioni();
		response = toRecensioniResponse(recensioni);
		logger.info("REST CALL: getRecensioni --> " + response); 
		return response;
	}
	
	/* Trova tutte le recensioni relative a un certo album (in formato breve). */ 
	@GetMapping("/cercarecensioni/album/{album}")
	public Collection<RecensioneBreveResponse> getRecensioniByAlbum(@PathVariable String album) {
		Collection<Recensione> recensioni = null; 
		Collection<RecensioneBreveResponse> response = null; 
		logger.info("REST CALL: getRecensioniByAlbum " + album); 
		recensioni = recensioniService.getRecensioniByAlbum(album);
		response = toRecensioniResponse(recensioni);
		logger.info("REST CALL: getRecensioniByAlbum " + album + " --> " + response); 
		return response;
	}

	/* Trova tutte le recensioni di un recensore (in formato breve). */ 
	@GetMapping("/cercarecensioni/recensore/{recensore}")
	public Collection<RecensioneBreveResponse> getRecensioniByRecensore(@PathVariable String recensore) {
		Collection<Recensione> recensioni = null; 
		Collection<RecensioneBreveResponse> response = null; 
		logger.info("REST CALL: getRecensioniByRecensore " + recensore); 
		recensioni = recensioniService.getRecensioniByRecensore(recensore);
		response = toRecensioniResponse(recensioni);
		logger.info("REST CALL: getRecensioniByRecensore " + recensore + " --> " + response); 
		return response;
	}

	/* Trova tutte le recensioni di un insieme di recensori (in formato breve). */ 
	@GetMapping("/cercarecensioni/recensori/{recensori}")
	public Collection<RecensioneBreveResponse> getRecensioniByRecensori(@PathVariable Collection<String> recensori) {
		Collection<Recensione> recensioni = null; 
		Collection<RecensioneBreveResponse> response = null; 
		logger.info("REST CALL: getRecensioniByRecensori " + recensori); 
		recensioni = recensioniService.getRecensioniByRecensori(recensori);
		response = toRecensioniResponse(recensioni);
		logger.info("REST CALL: getRecensioniByRecensori " + recensori + " --> " + response); 
		return response;
	}

	/* Trova tutte le recensioni relative a un certo artista (in formato breve). */ 
	@GetMapping("/cercarecensioni/artista/{artista}")
	public Collection<RecensioneBreveResponse> getRecensioniByArtista(@PathVariable String artista) {
		Collection<Recensione> recensioni = null; 
		Collection<RecensioneBreveResponse> response = null; 
		logger.info("REST CALL: getRecensioniByArtista " + artista); 
		recensioni = recensioniService.getRecensioniByArtista(artista);
		response = toRecensioniResponse(recensioni);
		logger.info("REST CALL: getRecensioniByArtista " + artista + " --> " + response); 
		return response;
	}

	/* Trova tutte le recensioni relative a un insieme di artisti (in formato breve). */ 
	@GetMapping("/cercarecensioni/artisti/{artisti}")
	public Collection<RecensioneBreveResponse> getRecensioniByArtisti(@PathVariable Collection<String> artisti) {
		Collection<Recensione> recensioni = null; 
		Collection<RecensioneBreveResponse> response = null; 
		logger.info("REST CALL: getRecensioniByArtisti " + artisti); 
		recensioni = recensioniService.getRecensioniByArtisti(artisti);
		response = toRecensioniResponse(recensioni);
		logger.info("REST CALL: getRecensioniByArtisti " + artisti + " --> " + response); 
		return response;
	}
	
	/* Trova tutte le recensioni relative a un certo genere (in formato breve). */ 
	@GetMapping("/cercarecensioni/genere/{genere}")
	public Collection<RecensioneBreveResponse> getRecensioniByGenere(@PathVariable String genere) {
		Collection<Recensione> recensioni = null; 
		Collection<RecensioneBreveResponse> response = null; 
		logger.info("REST CALL: getRecensioniByGenere " + genere); 
		recensioni = recensioniService.getRecensioniByGenere(genere);
		response = toRecensioniResponse(recensioni);
		logger.info("REST CALL: getRecensioniByGenere " + genere + " --> " + response); 
		return response;
	}

	/* Trova tutte le recensioni relative a un insieme di generi (in formato breve). */ 
	@GetMapping("/cercarecensioni/generi/{generi}")
	public Collection<RecensioneBreveResponse> getRecensioniByGeneri(@PathVariable Collection<String> generi) {
		Collection<Recensione> recensioni = null; 
		Collection<RecensioneBreveResponse> response = null; 
		logger.info("REST CALL: getRecensioniByArtisti " + generi); 
		recensioni = recensioniService.getRecensioniByGeneri(generi);
		response = toRecensioniResponse(recensioni);
		logger.info("REST CALL: getRecensioniByGeneri " + generi + " --> " + response); 
		return response;
	}
	
	/* Converte una recensione (in formato completo) in una risposta recensione (sempre in formato completo). */ 
	private RecensioneResponse toRecensioneResponse(Recensione recensione) {
		RecensioneResponse response = null; 
		if (recensione!=null) {
			response = new RecensioneResponse(recensione.getId(), recensione.getRecensore(), recensione.getAlbum(), 
					recensione.getArtista(), recensione.getGenere(), recensione.getTesto(), recensione.getSunto());
		} 
		return response; 
	}

	/* Converte una recensione (in formato completo) in una risposta recensione in formato breve. */ 
	private RecensioneBreveResponse toRecensioneBreveResponse(Recensione recensione) {
		RecensioneBreveResponse response = null; 
		if (recensione!=null) {
			response = new RecensioneBreveResponse(recensione.getId(), recensione.getRecensore(), recensione.getAlbum(), 
					recensione.getArtista(), recensione.getGenere(), recensione.getSunto());
		} 
		return response; 
	}

	/* Converte una collezione di recensioni (in formato completo) in una collezione di risposte recensione (in formato breve). */ 
	private Collection<RecensioneBreveResponse> toRecensioniResponse(Collection<Recensione> recensioni) {
		Collection<RecensioneBreveResponse> response = 
			recensioni
				.stream()
				.map(r -> toRecensioneBreveResponse(r))
				.collect(Collectors.toList());
		return response; 
	}

	private static String toString(String[] a) {
		return Arrays.toString(a); 
	}

}
