package Dades.Acces;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import Dades.Claus.PrimaryKey;
import Dades.Tipus.Alarma;
import Dades.Tipus.PartidaJugador;
import Dades.Tipus.TipusBD;

public abstract class Acces {
	
	protected abstract String[] getColumnNames();
	protected abstract String[] getColumnTypes();
	protected abstract String[] getConstraints();
	protected abstract String getTableName();
	protected Connection con;
	
	public void setConnection(Connection con){this.con = con;}
	public void closeconnection() throws SQLException{con.close();}
	
	public void CreateTable() throws Exception{
		String statement = "CREATE TABLE " + 
				getTableName() + " (" +
				getColumnsDefinition() +
				getConstraintsDefinition() +	
				");";
		con.createStatement().executeUpdate(statement);		
	}
	private  String getColumnsDefinition(){
		String ret = "";
		String[] ColumnNames = getColumnNames();
		String[] ColumnTypes = getColumnTypes();
		for (int i = 0; i < ColumnNames.length; i++){
			ret += ColumnNames[i] + " " + ColumnTypes[i] + ", ";
		}
		return ret;
	}
	
	
	private  String getConstraintsDefinition(){
		String ret = "";
		String[] Constraints = getConstraints();
		for (int i = 0; i < Constraints.length; i++){
			ret += Constraints[i];
			if (i < Constraints.length - 1) ret += ", ";
		}
		return ret;
	}
	
	public int getNBColumns(){
		return getColumnNames().length;
	}
	
	public String getPreparedInsert(){
		String ret = "";
		for (int i = 0; i < getNBColumns();i++){
			ret += "?";
			if (i < getNBColumns() - 1) ret+= ",";
		}
		return ret;
	}
	
	public String getPreparedAttributes(){
		String ret = "";
		return ret;
	}
	
	public boolean TableExists() throws Exception{
		DatabaseMetaData meta = con.getMetaData();
	    ResultSet rs = meta.getTables(null,null,getTableName().toLowerCase(),null);
		return rs.next();
		
	}
	public void DropTable() throws Exception{
		con.createStatement().executeUpdate("DROP TABLE " + getTableName());
	}

	public void DeleteAll() throws Exception{
		 con.createStatement().executeUpdate("TRUNCATE TABLE " + getTableName());
	}
	
	public void Insert(TipusBD t) throws Exception {          
        PreparedStatement ps = con.prepareStatement("INSERT INTO " + getTableName() + " VALUES (" + getPreparedInsert() +")");
        t.Escriure(ps);
        ps.executeUpdate();
	}
	
	public void Update(TipusBD t) throws Exception {          
		PreparedStatement ps = con.prepareStatement("UPDATE " + getTableName() + " WHERE " + getPreparedSelect() + " SET " );
        t.Escriure(ps);
        ps.executeUpdate();
	}
	public TipusBD get(PrimaryKey PK) throws Exception {		
		ResultSet rs;
		PreparedStatement ps = con.prepareStatement("SELECT FROM " + getTableName() + " WHERE " + getPreparedSelect());
		PK.EscriurePK(ps);
		rs = ps.executeQuery();	
		return LlegirResultat(rs);
	}
	
	public  void Delete(PrimaryKey PK) throws Exception {
		PreparedStatement ps = con.prepareStatement("DELETE FROM " + getTableName() + " WHERE " + getPreparedSelect());
		PK.EscriurePK(ps);
		ps.executeUpdate();
	}
	
	public  Set<TipusBD> getAll() throws Exception {
		Set<TipusBD> ret = new HashSet<>();
		ResultSet rs;
		rs = con.createStatement().executeQuery("SELECT * FROM " + getTableName());
		while (rs.next()) ret.add(LlegirResultat(rs));	
		return ret;
	}
	
	protected abstract String getPreparedSelect();
	protected abstract TipusBD LlegirResultat(ResultSet rs) throws SQLException;
	
	
	
}
