package business.clienti;
import java.util.GregorianCalendar;

public class Cliente {
	public Cliente(String nome, String cognome, String residenza, GregorianCalendar dataNascita, String CF, String numTelefonico, String email){
		this.nome=nome;
		this.cognome=cognome;
		this.residenza=residenza;
		this.dataNascita=(GregorianCalendar) dataNascita.clone();
		this.CF=CF;
		this.numTelefonico=numTelefonico;
		this.email=email;
	}
	public Cliente(String nome, String cognome, String residenza, GregorianCalendar dataNascita, String CF, String numTelefonico, String email, Integer id){
		this.nome=nome;
		this.cognome=cognome;
		this.residenza=residenza;
		this.dataNascita=(GregorianCalendar) dataNascita.clone();
		this.CF=CF;
		this.numTelefonico=numTelefonico;
		this.email=email;
		this.idPersistenza=id;
	}
	public Cliente(Cliente c){
		this.nome=c.nome;
		this.cognome=c.cognome;
		this.residenza=c.residenza;
		this.dataNascita=c.getDataNascita();
		this.CF=c.CF;
		this.numTelefonico=c.numTelefonico;
		this.email=c.email;
		this.idPersistenza=c.idPersistenza;
	}
	public String getResidenza() {
		return residenza;
	}
	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}
	public String getNumTelefonico() {
		return numTelefonico;
	}
	public void setNumTelefonico(String numTelefonico) {
		this.numTelefonico = numTelefonico;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIdPersistenza() {
		return idPersistenza;
	}
	public void setIdPersistenza(Integer id){
		this.idPersistenza=id;
	}
	public String getNome(){
		return nome;
	}
	public String getCognome(){
		return cognome;
	}
	public String getCF(){
		return CF;
	}
	public GregorianCalendar getDataNascita(){
		return (GregorianCalendar) dataNascita.clone();
	}
	
	private String nome;
	private String cognome;
	private String residenza;
	private GregorianCalendar dataNascita;
	private String CF;
	private String numTelefonico;
	private String email;
	private Integer idPersistenza;

}
