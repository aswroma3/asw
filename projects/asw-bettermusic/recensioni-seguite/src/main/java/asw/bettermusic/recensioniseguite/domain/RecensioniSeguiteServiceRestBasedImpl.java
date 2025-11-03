package asw.bettermusic.recensioniseguite.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Primary;

import java.util.*; 
import java.util.stream.*; 

@Service("RecensioniSeguiteServiceRestBasedImpl")
// @Primary 
public class RecensioniSeguiteServiceRestBasedImpl implements RecensioniSeguiteService {

	@Autowired 
	private ConnessioniClientPort connessioniClient;

	@Autowired 
	private RecensioniClientPort recensioniClient;

	@Autowired 
	private AlbumClientPort albumClient;

	/* Trova le recensioni seguite da un utente, 
	 * ovvero le recensioni degli album degli artisti, dei recensori e dei generi musicali seguiti da quell'utente. */ 
	public Collection<Recensione> getRecensioniSeguite(String utente) {
		Collection<Recensione> recensioniSeguite = new TreeSet<>(); 
		
		Collection<Connessione> connessioni = connessioniClient.getConnessioniByUtente(utente); 

		/* recensioni dei recensori seguiti */ 
		Collection<String> recensoriSeguiti = 
			connessioni
				.stream()
				.filter(c -> c.getRuolo().equals("RECENSORE"))
				.map(c -> c.getSeguito())
				.collect(Collectors.toSet()); 
		for (String recensore : recensoriSeguiti) { 
			Collection<Recensione> recensioniDiRecensoreSeguito = recensioniClient.getRecensioniByRecensore(recensore);
			recensioniSeguite.addAll(recensioniDiRecensoreSeguito); 
		}

		/* recensioni degli album degli artisti seguiti */ 
		Collection<String> artistiSeguiti = 
			connessioni
				.stream()
				.filter(c -> c.getRuolo().equals("ARTISTA"))
				.map(c -> c.getSeguito())
				.collect(Collectors.toSet()); 
		Collection<Recensione> recensioniDiArtistiSeguiti = new HashSet<>(); 
		Collection<Album> albumDiArtistiSeguiti = new HashSet<>(); 
		for (String artista : artistiSeguiti) {
			Collection<Album> albumDiArtistaSeguito = albumClient.getAlbumByArtista(artista);
			albumDiArtistiSeguiti.addAll(albumDiArtistaSeguito);
		}
		for (Album album : albumDiArtistiSeguiti) {
			Collection<Recensione> recensioniDiAlbumDiArtistiSeguiti = recensioniClient.getRecensioniByIdAlbum(album.getId());
			recensioniDiArtistiSeguiti.addAll(recensioniDiAlbumDiArtistiSeguiti); 
		}
		recensioniSeguite.addAll(recensioniDiArtistiSeguiti); 
		
		/* recensioni degli album dei generi seguiti */ 
		Collection<String> generiSeguiti = 
			connessioni
				.stream()
				.filter(c -> c.getRuolo().equals("GENERE"))
				.map(c -> c.getSeguito())
				.collect(Collectors.toSet()); 
		Collection<Recensione> recensioniDiGeneriSeguiti = new HashSet<>(); 
		Collection<Album> albumDiGeneriSeguiti = new HashSet<>(); 
		for (String genere : generiSeguiti) {
			Collection<Album> albumDiGenereSeguito = albumClient.getAlbumByGenere(genere);
			albumDiGeneriSeguiti.addAll(albumDiGenereSeguito);
		}
		for (Album album : albumDiGeneriSeguiti) {
			Collection<Recensione> recensioniDiAlbumDiGeneriSeguiti = recensioniClient.getRecensioniByIdAlbum(album.getId());
			recensioniDiGeneriSeguiti.addAll(recensioniDiAlbumDiGeneriSeguiti); 
		}
		recensioniSeguite.addAll(recensioniDiGeneriSeguiti); 

		return recensioniSeguite; 
	}

}
