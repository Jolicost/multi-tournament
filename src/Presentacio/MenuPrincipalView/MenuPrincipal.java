package Presentacio.MenuPrincipalView;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Presentacio.Comuns.PanellContenidor;
import Presentacio.Comuns.PanellGeneral;
import Presentacio.Comuns.PanellSeparador;

public class MenuPrincipal extends PanellGeneral {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuPrincipal(ActionListener Retrocedir,ActionListener Penjar,ActionListener VeureResultats,ActionListener VeureRanking,ActionListener VeureCalendari,ActionListener VeureProves){
		super(Retrocedir);
		PanellSeparador P = new PanellSeparador();
		
		JButton penjar = new JButton("Penjar Fitxer");
		penjar.addActionListener(Penjar);
		P.afegir(penjar);
		
		JButton resultats = new JButton("Veure Resultats");
		resultats.addActionListener(VeureResultats);
		P.afegir(resultats);
		
		JButton ranking= new JButton("Veure Ranking");
		ranking.addActionListener(VeureRanking);
		P.afegir(ranking);
		
		JButton calendari= new JButton("Veure Calendari");
		calendari.addActionListener(VeureCalendari);
		P.afegir(calendari);
		
		JButton proves= new JButton("Veure Proves");
		proves.addActionListener(VeureProves);
		P.afegir(proves);
	
		
		Centre.add(P);
	}

}
