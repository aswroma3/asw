package asw.goodmusic.recensioniseguite.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.*; 
import java.util.stream.*; 

@Service 
@Primary 
public class RecensioniSeguiteServiceRestbasedImpl implements RecensioniSeguiteService {

	@Autowired 
	private ConnessioniClientPort connessioniClient;

	@Autowired 
	private RecensioniClientPort recensioniClient;

	/* Trova le recensioni seguite da un utente, 
	 * ovvero le recensioni degli album degli artisti, dei recensori e dei generi musicali seguiti da quell'utente. */ 
	public Collection<RecensioneBreve> getRecensioniSeguite(String utente) {
		Collection<RecensioneBreve> recensioniSeguite = new TreeSet<>(); 
		
		Collection<Connessione> connessioni = connessioniClient.getConnessioniByUtente(utente); 

		Collection<String> artistiSeguiti = 
			connessioni
				.stream()
				.filter(c -> c.getRuolo().equals("ARTISTA"))
				.map(c -> c.getSeguito())
				.collect(Collectors.toSet()); 
		if (artistiSeguiti.size()>0) {
			Collection<RecensioneBreve> recensioniDiArtisti = recensioniClient.getRecensioniByArtisti(artistiSeguiti);
			recensioniSeguite.addAll(recensioniDiArtisti); 
		}
		
		Collection<String> recensoriSeguiti = 
			connessioni
				.stream()
				.filter(c -> c.getRuolo().equals("RECENSORE"))
				.map(c -> c.getSeguito())
				.collect(Collectors.toSet()); 
		if (recensoriSeguiti.size()>0) {
			Collection<RecensioneBreve> recensioniDiRecensori = recensioniClient.getRecensioniByRecensori(recensoriSeguiti);
			recensioniSeguite.addAll(recensioniDiRecensori); 
		}

		/* ok, ma purtroppo chiama il metodo getRecensioniByGeneri che non è definito né implementato */ 
//		Collection<String> generiSeguiti = 
//			connessioni
//				.stream()
//				.filter(c -> c.getRuolo().equals("GENERE"))
//				.map(c -> c.getSeguito())
//				.collect(Collectors.toSet()); 
//		if (generiSeguiti.size()>0) {
//			Collection<RecensioneBreve> recensioniDiGeneri = recensioniClient.getRecensioniByGeneri(generiSeguiti);
//			recensioniSeguite.addAll(recensioniDiGeneri); 
//		}

		return recensioniSeguite; 
	}

}
