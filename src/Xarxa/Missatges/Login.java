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
public class Login extends Paquet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String User;
    public String Pass;

    public Login(){}
    public Login(String Name,String Pass){
        User = Name;
        this.Pass = Pass;
    }
    public boolean EsLogin(){return true;}
    
    
    
}
