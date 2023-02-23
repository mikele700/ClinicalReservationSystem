package business.clienti;

import java.sql.SQLException;
import java.util.List;

import business.exceptions.NessunClienteTrovato;
import dao.ClienteDAO;
import dao.DBManager;

public class GestoreClienti implements IGestoreClienti{

	@Override
	public List<Cliente> ListaClienti() {
		List<Cliente> ListaClienti=new java.util.ArrayList<Cliente>();
		try {
			ListaClienti=ClienteDAO.readAll();
			DBManager.closeConnection();
		} catch (SQLException e) {
			System.err.println("Errore nel recupero dati.");
			System.err.println(e.getMessage());
			//e.printStackTrace();
		} catch (NessunClienteTrovato e) {
			System.err.println(e.getMessage());
		}
		return ListaClienti;
	}
	
	public Cliente RicercaCliente(Integer id){
		Cliente cl=null;
		if(id!=null){
			try {
				cl=ClienteDAO.read(id);
				DBManager.closeConnection();
			} catch (SQLException e) {
				System.err.println("Errore nel recupero dati.");
				//e.printStackTrace();
				System.exit(0);
			} catch (NessunClienteTrovato e) {
				System.err.println(e.getMessage());
				System.exit(0);
			}
		}
		return cl;
	}
	
	public static GestoreClienti getInstance(){
		if(instance==null)
			instance=new GestoreClienti();
		return instance;
	}
	protected GestoreClienti(){
	}
	private static GestoreClienti instance;

}
