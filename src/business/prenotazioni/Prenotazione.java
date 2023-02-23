package business.prenotazioni;
import java.util.GregorianCalendar;
import business.clienti.Cliente;
import business.stanze.Stanza;

public class Prenotazione {
	public Prenotazione(GregorianCalendar orario, String codiceAnalisi, Cliente cliente, Stanza stanza){
		this.orario=(GregorianCalendar) orario.clone();
		this.codiceAnalisi=codiceAnalisi;
		this.cliente=new Cliente(cliente);
		this.stanza=new Stanza(stanza);
		this.stato=statoPrenotazione.ASSEGNATA;
	}
	public Prenotazione(GregorianCalendar orario, String codiceAnalisi, Cliente cliente, Stanza stanza, Integer id){
		this.orario=(GregorianCalendar) orario.clone();
		this.codiceAnalisi=codiceAnalisi;
		this.cliente=new Cliente(cliente);
		this.stanza=new Stanza(stanza);
		this.stato=statoPrenotazione.ASSEGNATA;
		this.idPersistenza=id;
	}
	public Prenotazione(Prenotazione p){
		this.orario=p.getOrario();
		this.codiceAnalisi=p.getCodiceAnalisi();
		this.cliente=p.getCliente();
		this.stanza=p.getStanza();
		this.stato=p.stato;
	}
	
	public Integer getIdPersistenza() {
		return idPersistenza;
	}
	public void setIdPersistenza(Integer id) {
		this.idPersistenza = id;
	}
	public String getStato() {
		return stato.name();
	}
	public GregorianCalendar getOrario() {
		return (GregorianCalendar) orario.clone();
	}
	public String getCodiceAnalisi() {
		return codiceAnalisi;
	}
	public Cliente getCliente() {
		return new Cliente(cliente);
	}
	public Stanza getStanza() {
		return new Stanza(stanza);
	}
	
	protected void setStato(statoPrenotazione stato) {
		this.stato = stato;
	}
	
	protected static enum statoPrenotazione{ASSEGNATA, EFFETTUATA, ANNULLATA};
	public static final int DURATAMAX=30;//minuti
	private GregorianCalendar orario;
	private String codiceAnalisi;
	private Integer idPersistenza;
	private statoPrenotazione stato;
	private Cliente cliente;
	private Stanza stanza;

}
