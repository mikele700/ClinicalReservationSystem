package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.sql.Statement;
import business.exceptions.*;

import business.stanze.*;

public class StanzaDAO {
	public static void create(Stanza s) throws SQLException{
		Connection c=DBManager.getConnection();
		try(PreparedStatement pstat=c.prepareStatement("INSERT INTO STANZE(IDENTIFICATIVO) VALUES (?)")){
			pstat.setString(1, s.getIdentificativo());
			pstat.executeUpdate();
			ResultSet rs=pstat.getGeneratedKeys();
			if(!rs.next())
				throw new SQLException("L'ID non è stato autogenerato.");
			Integer id= rs.getInt(1);
			s.setIdPersistenza(id);
			restoredObjects.put(id, s);
		}
	}
	
	public static Stanza read(Integer id) throws SQLException, NessunaStanzaTrovata{
		if(id==null)
			throw new NessunaStanzaTrovata();
		if (restoredObjects.containsKey(id))
			return restoredObjects.get(id);
		Stanza s = null;
		Connection c = DBManager.getConnection();
		try(PreparedStatement pstat = c.prepareStatement("SELECT * FROM STANZE WHERE ID=?")){
			pstat.setInt(1, id);
			ResultSet rs = pstat.executeQuery();
			if(rs.next()){
				String identificativo=rs.getString("Identificativo");
				s=new Stanza(identificativo, id);
				restoredObjects.put(id, s);
			}
			else{
				throw new NessunaStanzaTrovata();
			}
			return s;
		}
	}
	
	public static List<Stanza> readAll() throws SQLException, NessunaStanzaTrovata{
		Connection c=DBManager.getConnection();
		Statement stat= c.createStatement();
		ResultSet rs=stat.executeQuery("SELECT * FROM STANZE");
		List<Stanza> ListaStanze=new java.util.ArrayList<Stanza>();
		while(rs.next()){
			Stanza s;
			Integer id=rs.getInt("ID");
			if(restoredObjects.containsKey(id))
				s = new Stanza(restoredObjects.get(id));
			else{
				String nome=rs.getString("Identificativo");
				s=new Stanza(nome, id);
				restoredObjects.put(id, s);
			}
			ListaStanze.add(s);
		}
		stat.close();
		if(ListaStanze.isEmpty())
			throw new NessunaStanzaTrovata();
		return ListaStanze;
	}
	
	public static void update(Stanza s) throws SQLException, StanzaInesistente{
		if(s.getIdPersistenza()==null)
			throw new StanzaInesistente();
		Connection c=DBManager.getConnection();
		PreparedStatement pstat=c.prepareStatement("UPDATE STANZE SET Identificativo=? WHERE ID=?");
		pstat.setString(1, s.getIdentificativo());
		pstat.setInt(2, s.getIdPersistenza());
		pstat.executeUpdate();
		pstat.close();
	}
	
	public static void delete(Stanza s) throws SQLException{
		if(s.getIdPersistenza()!=null){
			Connection c=DBManager.getConnection();
			PreparedStatement pstat=c.prepareStatement("DELETE FROM STANZE WHERE ID=?");
			pstat.setInt(1, s.getIdPersistenza());
			pstat.executeUpdate();
			pstat.close();
			restoredObjects.remove(s.getIdPersistenza());
			s.setIdPersistenza(null);
		}
		
	}
	protected static Map<Integer, Stanza> restoredObjects= new java.util.HashMap<Integer, Stanza>();
}
