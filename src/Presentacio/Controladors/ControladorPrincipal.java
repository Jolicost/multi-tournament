/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacio.Controladors;

import Excepcions.FitxerInvalid;
import Excepcions.InvalidLogin;
import Excepcions.NoHiHaFitxer;
import Presentacio.Comuns.Finestra;
import Presentacio.Comuns.PanellContenidor;
import Xarxa.Client.CapaDominiInterface;
import Xarxa.Client.Solicitant;
import Xarxa.Missatges.Login;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JOAN
 */
public class ControladorPrincipal {
    private Presentacio.LoginView.Login VistaLogin;
    private Presentacio.PenjarView.Penjar VistaPenjar;
    private CapaDominiInterface Domini;
    private Finestra Vista;
    private GestorContextual Gestor;
    
    public ControladorPrincipal() throws IOException{
    	Gestor = new GestorContextual();
        Domini = new Solicitant();
        Vista = new Finestra();
        MostrarLogin();   
    }
    
    private void MostrarLogin(){
    	 VistaLogin = new Presentacio.LoginView.Login(new LoginOK());
         CanviarContext("Login",VistaLogin);
    }
    private void MostrarPenjar(){
    	 VistaPenjar = new Presentacio.PenjarView.Penjar(new FitxerOK(),new Retrocedir());
    	 CanviarContext("Penjar Jugador",VistaPenjar);
    }
    public void Login(String User,String Pass){
        try {
            Domini.Login(User, Pass);
            MostrarPenjar();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            
        } catch (InvalidLogin ex) {
            VistaLogin.MostraMsg("Login Incorrecte");
        }
    }
    public void PenjarFitxer(){
    	try {
    		File f = VistaPenjar.getFitxer();
    		Domini.EnviarFitxer(f);
    		MostrarLogin();
    	}
    	catch (NoHiHaFitxer e){
    		VistaPenjar.MostraMsg("No hi ha fitxer");
    	} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		} catch (FitxerInvalid e) {
		}
    }
    
    private void CanviarContext(String titol,PanellContenidor Panell){
    	Panell.setTitol(titol);
    	Vista.CanviarContext(titol, Panell);
    	Gestor.NouContext(Panell);
    }
    
    public void Retrocedir(){
    	PanellContenidor p = Gestor.Retrocedir();
    	Vista.CanviarContext(p.getTitol(), p);
    }
    
    
    
    
    private class LoginOK implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Login(VistaLogin.getUserName(),VistaLogin.getPassword());
        }
        
    }
    
    private class FitxerOK implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		PenjarFitxer();
    	}
    }
    
    private class Retrocedir implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Retrocedir();
		}
    	
    }
}
