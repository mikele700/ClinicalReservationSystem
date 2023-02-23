package business.stanze;
import business.prenotazioni.Prenotazione;
import java.util.List;

public class Stanza {
	public Stanza(String nome){
		this.identificativo=nome;
	}
	public Stanza(String nome, Integer id){
		this.identificativo=nome;
		this.idPersistenza=id;
	}
	public Stanza(Stanza s){
		this.identificativo=s.identificativo;
		this.idPersistenza=s.idPersistenza;
	}
	
	public Integer getIdPersistenza() {
		return idPersistenza;
	}
	public void setIdPersistenza(Integer id) {
		this.idPersistenza = id;
	}
	public List<StrumentoClinico> getStrumenti() {
		return new java.util.ArrayList<StrumentoClinico>(ListaStrumenti);
	}
	public void setStrumenti(List<StrumentoClinico> listaStrumenti) {
		ListaStrumenti = new java.util.ArrayList<StrumentoClinico>(listaStrumenti);
	}
	public List<Prenotazione> getPrenotazioniAssegnate() {
		return new java.util.ArrayList<Prenotazione>(ListaPrenotazioniAssegnate);
	}
	public void setPrenotazioniAssegnate(List<Prenotazione> listaPrenotazioni) {
		ListaPrenotazioniAssegnate =new java.util.ArrayList<Prenotazione>(listaPrenotazioni);
	}
	public String getIdentificativo() {
		return identificativo;
	}

	private String identificativo;
	private Integer idPersistenza;
	private List<StrumentoClinico> ListaStrumenti;
	private List<Prenotazione> ListaPrenotazioniAssegnate;

}
