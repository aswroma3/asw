package asw.goodmusic.recensioniseguite.domain;

import java.util.*; 

public interface RecensioniClientPort {

	public Collection<RecensioneBreve> getRecensioniByArtisti(Collection<String> artisti); 

	public Collection<RecensioneBreve> getRecensioniByRecensori(Collection<String> recensori); 

}
