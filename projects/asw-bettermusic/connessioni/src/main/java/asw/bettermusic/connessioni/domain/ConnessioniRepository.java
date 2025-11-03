package asw.bettermusic.connessioni.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface ConnessioniRepository extends CrudRepository<Connessione, Long> {

	Collection<Connessione> findAll();

	Collection<Connessione> findByUtente(String utente);

	Collection<Connessione> findByRuolo(String ruolo);

	Collection<Connessione> findByUtenteAndRuolo(String utente, String ruolo);

	Connessione findByUtenteAndSeguitoAndRuolo(String utente, String seguito, String ruolo);

}

