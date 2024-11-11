package asw.goodmusic.recensioni.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*; 

public interface RecensioniRepository extends CrudRepository<Recensione, Long> {

	public Collection<Recensione> findAll();

	public Collection<Recensione> findByRecensore(String recensore);

	public Collection<Recensione> findByRecensoreIn(Collection<String> recensori);

	public Collection<Recensione> findByAlbum(String album);

	public Collection<Recensione> findByArtista(String artista);

	public Collection<Recensione> findByArtistaIn(Collection<String> artisti);

	public Collection<Recensione> findByGenere(String genere);

	public Collection<Recensione> findByGenereIn(Collection<String> generi);

}

