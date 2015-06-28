/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xarxa.Client;

import Comunicacio.InfoJugadorRanking;
import Comunicacio.InfoPartida;
import Domini.Fitxers.FitxerJugador;
import Excepcions.FitxerInvalid;
import Excepcions.InvalidLogin;
import Xarxa.Missatges.Login;
import Xarxa.Missatges.LoginResponse;
import Xarxa.Missatges.Paquet;
import Xarxa.Sockets.PaquetSocket;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.SortedSet;

/**
 *
 * @author JOAN
 */
public class Solicitant implements CapaDominiInterface {
    private PaquetSocket s;
    
    public Solicitant() throws IOException{
        //llegir ip i port d'algun lloc
         s = new PaquetSocket("92.59.7.175",4000);
        //this.s = new PaquetSocket(s);
    }
    
    @Override
    public void Login(String User,String Pass) throws IOException, ClassNotFoundException, InvalidLogin{
         s.Escriure(new Xarxa.Missatges.Login(User,Pass));
    	 LoginResponse r = s.Llegir().LoginResponseCast();
    	 if (! r.autoritzat) throw new InvalidLogin();   
    }
    

	@Override
	public void EnviarFitxer(File f) throws IOException, ClassNotFoundException, FitxerInvalid{
        s.Escriure(new Xarxa.Missatges.PenjarJugador(new FitxerJugador(f)));
        if (!s.Llegir().PenjarJugadorResponseCast().valid) throw new FitxerInvalid();
	}
	
	public SortedSet<InfoPartida> ConsultarResultats() throws IOException, ClassNotFoundException {
		s.Escriure(new Xarxa.Missatges.ConsultarResultats());
		return s.Llegir().ConsultarResultatsResponseCast().getInfo();
	}
	
	public  SortedSet<InfoJugadorRanking> ConsultarClassificacio() throws IOException, ClassNotFoundException{
		s.Escriure(new Xarxa.Missatges.ConsultarClassificacio());
		return s.Llegir().ConsultarClassificacioResponseCast().getInfo();
	}
	
	public File VisualitzarPartida(int ID) throws IOException, ClassNotFoundException{
		s.Escriure(new Xarxa.Missatges.VisualitzarPartida(ID));
		return s.Llegir().VisualitzarPartidaResponseCast().getPartida().getFile();
	}
    
   
}
