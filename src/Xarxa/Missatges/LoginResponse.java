/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xarxa.Missatges;

/**
 *
 * @author JOAN
 */
public class LoginResponse extends Paquet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean autoritzat;
    
    public boolean EsLoginResponse(){return true;}
}
