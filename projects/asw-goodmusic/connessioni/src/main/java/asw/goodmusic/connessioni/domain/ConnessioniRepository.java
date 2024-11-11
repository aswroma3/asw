package asw.goodmusic.connessioni.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface ConnessioniRepository extends CrudRepository<Connessione, Long> {

	public Collection<Connessione> findAll();

	public Collection<Connessione> findByUtente(String utente);

	public Collection<Connessione> findByRuolo(String ruolo);

	public Collection<Connessione> findByUtenteAndRuolo(String utente, String ruolo);

	public Connessione findByUtenteAndSeguitoAndRuolo(String utente, String seguito, String ruolo);

}

