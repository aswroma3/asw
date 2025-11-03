package asw.bettermusic.recensioniseguite.rest;

import asw.bettermusic.recensioniseguite.domain.*; 

import asw.bettermusic.recensioniseguite.api.rest.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.Instant; 
import java.time.Duration; 

import java.util.*; 
import java.util.stream.*; 

import java.util.logging.Logger; 

@RestController
public class RecensioniSeguiteController {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired 
	@Qualifier("${asw.recensioniseguiteservice.implementation}")
	private RecensioniSeguiteService recensioniSeguiteService;

	/* Trova le recensioni seguite da un utente. */ 
	@GetMapping("/recensioniseguite/{utente}")
	public Collection<RecensioneSeguitaResponse> getRecensioniSeguite(@PathVariable String utente) {
		Instant start = Instant.now();
		logger.info("REST CALL: getRecensioniSeguite " + utente); 
		Collection<Recensione> recensioni = recensioniSeguiteService.getRecensioniSeguite(utente); 
		Collection<RecensioneSeguitaResponse> response = toRecensioniSeguiteResponse(recensioni); 

		Duration duration = Duration.between(start, Instant.now()); 
		logger.info("getRecensioniSeguite " + utente 
		            + " --> trovate " + recensioni.size() + " recensioni in " + duration.toMillis() + " ms" 
					+ " --> " + response);
		return response; 
	}

	/* Converte una recensione in una risposta recensione seguita. */ 
	private RecensioneSeguitaResponse toRecensioneSeguitaResponse(Recensione recensione) {
		RecensioneSeguitaResponse response = null; 
		if (recensione!=null) {
			response = new RecensioneSeguitaResponse(recensione.getId(), recensione.getRecensore(), 
					recensione.getIdAlbum(), recensione.getSunto());
		} 
		return response; 
	}

	/* Converte una collezione di recensioni in una collezione di risposte recensione seguita. */ 
	private Collection<RecensioneSeguitaResponse> toRecensioniSeguiteResponse(Collection<Recensione> recensioni) {
		Collection<RecensioneSeguitaResponse> response = 
			recensioni
				.stream()
				.map(r -> toRecensioneSeguitaResponse(r))
				.collect(Collectors.toList());
		return response; 
	}
	
}
