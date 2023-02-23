package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
	
	public static Connection getConnection(){
		if (conn == null) {		//quando è chiusa conn==null grazie al metodo closeConnection()
			try {
		        conn = CONNECTION_FACTORY.createConnection();
		    } catch(Exception e) {
		    	System.err.println("Errore nella connessione al DB.");
		    	System.err.println(e.getMessage());
		        //e.printStackTrace();
	        }
		}
		return conn;
	}
	
	public static void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			conn = null;
		}
	}
	
	protected static Connection conn;
	final protected static ConnectionFactory CONNECTION_FACTORY = new H2ConnectionFactory();
}
