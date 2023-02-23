package business.prenotazioni;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import business.clienti.Cliente;
import business.exceptions.ClienteInesistente;
import business.exceptions.IndisponibilitaPrenotazione;
import business.exceptions.NessunaPrenotazioneAssegnataAllaStanzaTrovata;
import business.exceptions.StanzaInesistente;
import business.stanze.GestoreStanze;
import dao.DBManager;
import dao.PrenotazioneDAO;
import business.stanze.*;

public class GestorePrenotazioni implements IGestorePrenotazioni{
	
	public Prenotazione RegistraPrenotazione(GregorianCalendar orario, String codiceAnalisi, Cliente cliente) throws IndisponibilitaPrenotazione{
		GestoreStanze gestoreStanze=GestoreStanze.getInstance();
		List<Stanza> ListaStanze=new java.util.ArrayList<Stanza>();
		ListaStanze=gestoreStanze.ListaStanze();
		for(Stanza stanza: ListaStanze){
			List<StrumentoClinico> ListaStrumentiPerStanza=new java.util.ArrayList<StrumentoClinico>();
			ListaStrumentiPerStanza=gestoreStanze.ListaStrumentiPerStanza(stanza);
			for(StrumentoClinico strumento: ListaStrumentiPerStanza){
				String codice=strumento.getCodiceAnalisi();
				if(codiceAnalisi.equals(codice)){
					boolean stanzaDisponibile=true;
					List<Prenotazione> ListaPrenotazioniAssegnatePerStanza= new java.util.ArrayList<Prenotazione>();
					ListaPrenotazioniAssegnatePerStanza=this.ListaPrenotazioniAssegnatePerStanza(stanza);
					GregorianCalendar limMin=(GregorianCalendar)orario.clone();
					limMin.add(Calendar.MINUTE, -(Prenotazione.DURATAMAX));
					GregorianCalendar limMax=(GregorianCalendar)orario.clone();
					limMax.add(Calendar.MINUTE, Prenotazione.DURATAMAX);
					for(int i=0; (i<ListaPrenotazioniAssegnatePerStanza.size())&&(stanzaDisponibile); i++)
						if((ListaPrenotazioniAssegnatePerStanza.get(i).getOrario().compareTo(limMax)<=0)&&(ListaPrenotazioniAssegnatePerStanza.get(i).getOrario().compareTo(limMin)>=0))
							stanzaDisponibile=false;
					if(stanzaDisponibile){
						Prenotazione prenotazioneDaRegistrare=new Prenotazione(orario, codiceAnalisi, cliente, stanza);
						try {
							PrenotazioneDAO.create(prenotazioneDaRegistrare);
							DBManager.closeConnection();
							return prenotazioneDaRegistrare;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.err.println("Errore nella memorizzazione.");
							System.err.println(e.getMessage());
							//e.printStackTrace();
						} catch (StanzaInesistente e) {
							// TODO Auto-generated catch block
							System.err.println(e.getMessage());
						} catch (ClienteInesistente e) {
							// TODO Auto-generated catch block
							System.err.println(e.getMessage());
						}
					}
				}
			}
		}
		throw new IndisponibilitaPrenotazione();
	}
	
	public List<Prenotazione> ListaPrenotazioniAssegnatePerStanza(Stanza stanza){
		List<Prenotazione> ListaPrenotazioniAssegnatePerStanza=new java.util.ArrayList<Prenotazione>();
		try {
			ListaPrenotazioniAssegnatePerStanza=PrenotazioneDAO.leggiPrenotazioniAssegnatePerStanza(stanza);
			DBManager.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Errore nel recupero dati.");
			System.err.println(e.getMessage());
			//e.printStackTrace();
		} catch (NessunaPrenotazioneAssegnataAllaStanzaTrovata e) {
			System.err.println(e.getMessage());
		}
		return ListaPrenotazioniAssegnatePerStanza;
	}
	public void AnnullaPrenotazione(Prenotazione prenotazioneDaAnnullare){	
	}
	public static GestorePrenotazioni getInstance(){
		if(instance==null)
			instance=new GestorePrenotazioni();
		return instance;
	}
	protected GestorePrenotazioni(){
	}
	private static GestorePrenotazioni instance;

}
