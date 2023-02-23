package business.prenotazioni;
import java.util.GregorianCalendar;
import java.util.List;

import business.clienti.Cliente;
import business.exceptions.IndisponibilitaPrenotazione;
import business.stanze.Stanza;

public interface IGestorePrenotazioni {
	public Prenotazione RegistraPrenotazione(GregorianCalendar orario, String codiceAnalisi, Cliente cliente) throws IndisponibilitaPrenotazione;
	public void AnnullaPrenotazione(Prenotazione prenotazioneDaAnnullare);
	public List<Prenotazione> ListaPrenotazioniAssegnatePerStanza(Stanza stanza);
}
