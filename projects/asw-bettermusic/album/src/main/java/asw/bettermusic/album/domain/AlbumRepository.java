package asw.bettermusic.album.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*; 

public interface AlbumRepository extends CrudRepository<Album, Long> {

 	Optional<Album> findByTitoloAndArtista(String titolo, String artista); 

	Collection<Album> findAll();

	Collection<Album> findByArtista(String artista);

//	Collection<Album> findByArtistaIn(Collection<String> artisti);

	@Query(value = "SELECT a FROM Album a JOIN a.generi g WHERE g = :genere")
	Collection<Album> findByGenere(String genere);

//	@Query(value = "SELECT a FROM Album a JOIN a.generi g WHERE g IN :generi")
//	Collection<Album> findByGenereIn(Collection<String> generi);

}

