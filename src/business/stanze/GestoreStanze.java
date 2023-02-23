package business.stanze;

import business.exceptions.*;
import dao.DBManager;
import dao.StanzaDAO;
import dao.StrumentoClinicoDAO;

import java.sql.SQLException;
import java.util.List;

public class GestoreStanze implements IGestoreStanze{
	
	public static GestoreStanze getInstance(){
		if(instance==null)
			instance=new GestoreStanze();
		return instance;
	}
	public List<Stanza> ListaStanze(){
		List<Stanza> ListaStanze=new java.util.ArrayList<Stanza>();
		try {
			ListaStanze = StanzaDAO.readAll();
			DBManager.closeConnection();
		} catch(NessunaStanzaTrovata t){
			System.err.println(t.getMessage());
		}
		catch (SQLException e) {
			System.err.println("Errore nel recupero dati.");
			System.err.println(e.getMessage());
			//e.printStackTrace();
		}
		return ListaStanze;
	}
	
	public List<StrumentoClinico> ListaStrumentiPerStanza(Stanza stanza){
		List<StrumentoClinico> ListaStrumentiPerStanza= new java.util.ArrayList<StrumentoClinico>();
			try {
				ListaStrumentiPerStanza=StrumentoClinicoDAO.leggiStrumentiPerStanza(stanza);
				DBManager.closeConnection();
			} catch (SQLException e) {
				System.err.println("Errore nel recupero dati");
				System.err.println(e.getMessage());
				//e.printStackTrace();
			} catch (DatoInputErrato e) {
				System.err.println(e.getMessage());
			} catch (NessunoStrumentoInStanzaTrovato e) {
				System.err.println(e.getMessage());
			} catch (StanzaInesistente e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		return ListaStrumentiPerStanza;
	}
	protected GestoreStanze(){
	}
	private static GestoreStanze instance;
}
