package Comunicacio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class Fitxer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private byte[] dades;
	private String nom;
	
	public Fitxer(File f) throws IOException{
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
		dades = new byte[(int)f.length()];
		in.read(dades);
		nom = f.getName();
		
		in.close();
	}

	public Fitxer(String nom,byte[] dades){
		this.nom = nom;
		this.dades = dades;
	}
	
	public Fitxer(){
		
	}
	public byte[] getDades(){return dades;}
	
	public File getFile() throws IOException{
		File f = new File(System.getProperty("user.dir") + File.separator + nom);
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
		out.write(dades);
		out.close();
		return f;
	}
	public File WriteFile(String Ruta) throws IOException{
		File f = new File(Ruta);
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
		out.write(dades);
		out.close();
		return f;
	}
	
	public File WriteFileInDir(String Dir) throws IOException{
		File f = new File(Dir + File.separator + nom);
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
		out.write(dades);
		out.close();
		return f;
	}
	
	public String getNomSenseExt(){
		return stripExtension(nom);
	}
	
	
	public String getNom(){
		return nom;
	}
	
	private static String stripExtension (String str) {
        if (str == null) return null;
        int pos = str.lastIndexOf(".");
        if (pos == -1) return str;
        return str.substring(0, pos);
    }
	public void setDades(byte[] dades) {
		this.dades = dades;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
