package Xarxa.Missatges;

import Updater.Release.ClientJar;

public class DescarregarJarResponse extends Paquet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientJar j;
	public DescarregarJarResponse(ClientJar j){this.j = j;}
	public ClientJar getJar(){return j;}
    public boolean EsDescarregarJarResponse(){return true;}
}
