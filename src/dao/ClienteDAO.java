package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import business.clienti.Cliente;
import business.exceptions.NessunClienteTrovato;

public class ClienteDAO {
	protected static Map<Integer, Cliente> restoredObjects= new java.util.HashMap<Integer, Cliente>();
	
	public static void create(Cliente cl) throws SQLException{
		Connection c=DBManager.getConnection();
		try(PreparedStatement pstat=c.prepareStatement("INSERT INTO CLIENTI(Nome, Cognome, Residenza, DataNascita, CF, NumTelefonico, Email) VALUES (?,?,?,?,?,?,?)")){
			pstat.setString(1, cl.getNome());
			pstat.setString(2, cl.getCognome());
			pstat.setString(3, cl.getResidenza());
			pstat.setDate(4, new java.sql.Date(cl.getDataNascita().getTimeInMillis()));
			pstat.setString(5, cl.getCF());
			pstat.setString(6, cl.getNumTelefonico());
			pstat.setString(7, cl.getEmail());
			pstat.executeUpdate();
			ResultSet rs=pstat.getGeneratedKeys();
			if(!rs.next())
				throw new SQLException("L'ID non è stato autogenerato.");
			Integer id= rs.getInt(1);
			cl.setIdPersistenza(id);
			restoredObjects.put(id, cl);
		}
	}
	
	public static List<Cliente> readAll() throws SQLException, NessunClienteTrovato{
		Connection c=DBManager.getConnection();
		Statement stat= c.createStatement();
		ResultSet rs=stat.executeQuery("SELECT * FROM CLIENTI");
		List<Cliente> ListaClienti=new java.util.ArrayList<Cliente>();
		while(rs.next()){
			Cliente cl;
			Integer id=rs.getInt("ID");
			if(restoredObjects.containsKey(id))
				cl = new Cliente(restoredObjects.get(id));
			else{
				GregorianCalendar dataNascita= new GregorianCalendar();
				dataNascita.setTime(rs.getDate("DataNascita"));
				cl=new Cliente(rs.getString("Nome"),rs.getString("Cognome"), rs.getString("Residenza"), dataNascita, rs.getString("CF"), rs.getString("NumTelefonico"), rs.getString("Email"), id);
				restoredObjects.put(id, cl);
			}
			ListaClienti.add(cl);
		}
		stat.close();
		if(ListaClienti.isEmpty())
			throw new NessunClienteTrovato();
		return ListaClienti;
	}
	
	public static Cliente read(Integer id) throws SQLException, NessunClienteTrovato{
		if(id==null)
			throw new NessunClienteTrovato();
		if (restoredObjects.containsKey(id))
			return restoredObjects.get(id);
		Cliente cl = null;
		Connection c = DBManager.getConnection();
		try(PreparedStatement pstat = c.prepareStatement("SELECT * FROM CLIENTI WHERE ID=?")){
			pstat.setInt(1, id);
			ResultSet rs = pstat.executeQuery();
			if(rs.next()){
				GregorianCalendar dataNascita= new GregorianCalendar();
				dataNascita.setTime(rs.getDate("DataNascita"));
				cl=new Cliente(rs.getString("Nome"),rs.getString("Cognome"), rs.getString("Residenza"), dataNascita, rs.getString("CF"), rs.getString("NumTelefonico"), rs.getString("Email"), id);
				restoredObjects.put(id, cl);
			}
			else
				throw new NessunClienteTrovato();
			return cl;
		}
	}
}
