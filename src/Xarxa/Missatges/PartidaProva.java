package Xarxa.Missatges;

import java.util.Set;

public class PartidaProva extends Paquet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PartidaProva(Set<String> jugadors) {
		this.jugadors = jugadors;
	}
	private Set<String> jugadors;
	
    public boolean EsPartidaProva(){return true;}
    
	public Set<String> getJugadors() {
		return jugadors;
	}
	public void setJugadors(Set<String> jugadors) {
		this.jugadors = jugadors;
	}
}
