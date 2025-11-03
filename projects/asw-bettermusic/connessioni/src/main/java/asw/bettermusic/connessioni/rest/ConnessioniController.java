package asw.bettermusic.connessioni.rest;

import asw.bettermusic.connessioni.domain.*;

import asw.bettermusic.connessioni.api.rest.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class ConnessioniController {

	@Autowired 
	private ConnessioniService connessioniService; 

    private final Logger logger = Logger.getLogger(this.getClass().toString());

	/* Crea una nuova connessione. */ 
	@PostMapping("/connessioni")
	public ConnessioneResponse createConnessione(@RequestBody CreateConnessioneRequest request) {
		String utente = request.getUtente();
		String seguito = request.getSeguito();
		String ruolo = request.getRuolo();
		logger.info("REST CALL: createConnessione " + utente + ", " + seguito + ", " + ruolo); 
		Connessione connessione = connessioniService.createConnessione(utente, seguito, ruolo);
		if (connessione!=null) {
			ConnessioneResponse response = toConnessioneResponse(connessione); 
			logger.info("REST CALL: createConnessione " + utente + ", " + seguito + ", " + ruolo + " --> " + response); 
			return response; 
		} else {
			logger.info("REST CALL: createConnessione " + utente + ", " + seguito + ", " + ruolo + " --> NOT CREATED"); 
			throw new ResponseStatusException(
				HttpStatus.INTERNAL_SERVER_ERROR, "Connessione not created"
			);
		}
	}	

	/* Trova tutte le connessioni. */ 
	@GetMapping("/connessioni")
	public Collection<ConnessioneResponse> getConnessioni() {
		Collection<Connessione> connessioni = null; 
		logger.info("REST CALL: getConnessioni"); 
		connessioni = connessioniService.getConnessioni();
		Collection<ConnessioneResponse> response = toConnessioniResponse(connessioni);
		logger.info("REST CALL: getConnessioni --> " + response); 
		return response;
	}

	/* Trova tutte le connessioni di un utente. */ 
	@GetMapping("/connessioni/{utente}")
	public Collection<ConnessioneResponse> getConnessioniByUtente(@PathVariable String utente) {
		Collection<Connessione> connessioni = null; 
		logger.info("REST CALL: getConnessioniByUtente " + utente); 
		connessioni = connessioniService.getConnessioniByUtente(utente);
		Collection<ConnessioneResponse> response = toConnessioniResponse(connessioni);
		logger.info("REST CALL: getConnessioniByUtente " + utente + " --> " + response); 
		return response;
	}

	/* Trova tutte le connessioni di un utente con un certo ruolo. */ 
	@GetMapping("/connessioni/{utente}/{ruolo}")
	public Collection<ConnessioneResponse> getConnessioniByUtenteAndRuolo(@PathVariable String utente, @PathVariable String ruolo) {
		Collection<Connessione> connessioni = null; 
		logger.info("REST CALL: getConnessioniByUtenteAndRuolo " + utente + " " + ruolo); 
		connessioni = connessioniService.getConnessioniByUtenteAndRuolo(utente, ruolo);
		Collection<ConnessioneResponse> response = toConnessioniResponse(connessioni);
		logger.info("REST CALL: getConnessioniByUtenteAndRuolo " + utente + " " + ruolo + " --> " + response); 
		return response;
	}

	/* Cancella una connessione. */ 
	@DeleteMapping("/connessioni")
	public ConnessioneResponse deleteConnessione(@RequestBody DeleteConnessioneRequest request) {
		String utente = request.getUtente();
		String seguito = request.getSeguito();
		String ruolo = request.getRuolo();
		logger.info("REST CALL: deleteConnessione " + utente + ", " + seguito + ", " + ruolo); 
		Connessione connessione = connessioniService.deleteConnessione(utente, seguito, ruolo);
		if (connessione!=null) {
			ConnessioneResponse response = toConnessioneResponse(connessione); 
			logger.info("REST CALL: deleteConnessione " + utente + ", " + seguito + ", " + ruolo + " --> " + response + " *** DELETED ***"); 
			return response; 
		} else {
			logger.info("REST CALL: deleteConnessione " + utente + ", " + seguito + ", " + ruolo + " --> NOT DELETED"); 
			throw new ResponseStatusException(
				HttpStatus.INTERNAL_SERVER_ERROR, "Connessione not deleted"
			);
		}
	}	

	/* Converte una connessione in una risposta connessione. */ 
	private ConnessioneResponse toConnessioneResponse(Connessione connessione) {
		ConnessioneResponse response = null; 
		if (connessione!=null) {
			response = new ConnessioneResponse(connessione.getId(), connessione.getUtente(), 
					connessione.getSeguito(), connessione.getRuolo());
		} 
		return response; 
	}

	/* Converte una collezione di connessioni in una collezione di risposte connessione. */ 
	private Collection<ConnessioneResponse> toConnessioniResponse(Collection<Connessione> connessioni) {
		Collection<ConnessioneResponse> response = 
			connessioni
				.stream()
				.map(c -> toConnessioneResponse(c))
				.collect(Collectors.toList());
		return response; 
	}

}
