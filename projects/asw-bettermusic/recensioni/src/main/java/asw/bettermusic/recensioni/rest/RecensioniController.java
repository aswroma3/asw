package asw.bettermusic.recensioni.rest;

import asw.bettermusic.recensioni.domain.*;

import asw.bettermusic.recensioni.api.rest.*;

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
		String titoloAlbum = request.getTitoloAlbum();
		String artistaAlbum = request.getArtistaAlbum();
		String testo = request.getTesto();
		String sunto = request.getSunto();
		logger.info("REST CALL: createRecensione " + recensore + ", " + titoloAlbum + ", " + artistaAlbum + ", " + testo + ", " + sunto); 
		Recensione recensione = recensioniService.createRecensione(recensore, titoloAlbum, artistaAlbum, testo, sunto);
		RecensioneResponse response = toRecensioneResponse(recensione); 
		logger.info("REST CALL: createRecensione " + recensore + ", " + titoloAlbum + ", " + artistaAlbum + ", " + testo + ", " + sunto 
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
	
	/* Trova tutte le recensioni relative a un certo album, dato l'id (in formato breve). */ 
	@GetMapping("/cercarecensioni/album/{idAlbum}")
	public Collection<RecensioneBreveResponse> getRecensioniByIdAlbum(@PathVariable Long idAlbum) {
		Collection<Recensione> recensioni = null; 
		Collection<RecensioneBreveResponse> response = null; 
		logger.info("REST CALL: getRecensioniByIdAlbum " + idAlbum); 
		recensioni = recensioniService.getRecensioniByIdAlbum(idAlbum);
		response = toRecensioniResponse(recensioni);
		logger.info("REST CALL: getRecensioniByIdAlbum " + idAlbum + " --> " + response); 
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
	
	/* Converte una recensione (in formato completo) in una risposta recensione (sempre in formato completo). */ 
	private RecensioneResponse toRecensioneResponse(Recensione recensione) {
		RecensioneResponse response = null; 
		if (recensione!=null) {
			response = new RecensioneResponse(recensione.getId(), recensione.getRecensore(), 
					recensione.getIdAlbum(), recensione.getTesto(), recensione.getSunto());
		} 
		return response; 
	}

	/* Converte una recensione (in formato completo) in una risposta recensione in formato breve. */ 
	private RecensioneBreveResponse toRecensioneBreveResponse(Recensione recensione) {
		RecensioneBreveResponse response = null; 
		if (recensione!=null) {
			response = new RecensioneBreveResponse(recensione.getId(), recensione.getRecensore(), 
					recensione.getIdAlbum(), recensione.getSunto());
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

}
