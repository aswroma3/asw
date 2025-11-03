package asw.bettermusic.album.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumRepository albumRepository;

	/* Crea un nuovo album, a partire dai suoi dati. */ 
	public Album createAlbum(String titolo, String artista, Set<String> generi) {
		Album album = new Album(titolo, artista, generi); 
		try {
			album = albumRepository.save(album);
			return album;
		} catch(Exception e) {
			/* si potrebbe verificare un'eccezione se è violato il vincolo di unicità dell'album */ 
			return null; 
		}
	}

	/* Trova un album, dato l'id. */ 
 	public Album getAlbum(Long id) {
		Album album = albumRepository.findById(id).orElse(null);
		return album;
	}

	/* Trova un album, dati titolo e artista. */ 
 	public Album getAlbum(String titolo, String artista) {
		Album album = albumRepository.findByTitoloAndArtista(titolo, artista).orElse(null);
		return album;
	}

	/* Trova tutti gli album. */ 
	public Collection<Album> getAlbum() {
		Collection<Album> albums = albumRepository.findAll();
		return albums; 
	}

	/* Trova tutti gli album di un artista. */ 
	public Collection<Album> getAlbumByArtista(String artista) {
		Collection<Album> albums = albumRepository.findByArtista(artista);
		return albums; 
	} 

	/* Trova tutti gli album di un genere. */ 
	public Collection<Album> getAlbumByGenere(String genere) {
		Collection<Album> albums = albumRepository.findByGenere(genere);
		return albums; 
	} 

}
