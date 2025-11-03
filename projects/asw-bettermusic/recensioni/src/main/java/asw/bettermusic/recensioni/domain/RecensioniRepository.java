package asw.bettermusic.recensioni.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*; 

public interface RecensioniRepository extends CrudRepository<Recensione, Long> {

	Collection<Recensione> findAll();

	Collection<Recensione> findByRecensore(String recensore);

	Collection<Recensione> findByIdAlbum(Long idAlbum);

}

