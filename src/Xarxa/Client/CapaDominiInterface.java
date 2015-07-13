/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xarxa.Client;

import java.io.File;
import java.io.IOException;
import java.util.SortedSet;

import Comunicacio.InfoCalendariPartida;
import Comunicacio.InfoJugadorRanking;
import Comunicacio.InfoPartida;
import Excepcions.FitxerInvalid;
import Excepcions.InvalidLogin;

/**
 *
 * @author JOAN
 */
public interface CapaDominiInterface {
    public void Login(String User,String Pass) throws IOException, ClassNotFoundException, InvalidLogin, Exception;
    
    public void EnviarFitxer(File f) throws IOException, ClassNotFoundException, FitxerInvalid, Exception;
    
    public SortedSet<InfoPartida> ConsultarResultats() throws IOException, ClassNotFoundException, Exception;
    
    public  SortedSet<InfoJugadorRanking> ConsultarClassificacio() throws IOException, ClassNotFoundException, Exception;
    
    public File VisualitzarPartida(int ID) throws IOException, ClassNotFoundException, Exception;
    
    public void Registrarse(String User,String pass) throws Exception;
    
    public SortedSet<InfoCalendariPartida> ConsultarCalendari() throws Exception;
    
    public void Finalitzar() throws Exception;
    
}
