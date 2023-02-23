package business.stanze;

import business.exceptions.DatoInputErrato;

public class StrumentoClinico {
	public static enum tipoStrumento{RX, TAC, PET, RM};
	public StrumentoClinico(String codice, tipoStrumento tipo){
		this.codiceAnalisi=codice;
		this.tipo=tipo;
	}
	public StrumentoClinico(String codice, tipoStrumento tipo, Integer id){
		this.codiceAnalisi=codice;
		this.tipo=tipo;
		this.idPersistenza=id;
	}
	public StrumentoClinico(StrumentoClinico sc){
		this.codiceAnalisi=sc.codiceAnalisi;
		this.tipo=sc.tipo;
		this.idPersistenza=sc.idPersistenza;
	}
	public static tipoStrumento StringToTipoStrumento(String s) throws DatoInputErrato{
		tipoStrumento t=null;
		switch(s){
		case "RX":
			t= tipoStrumento.RX;
			break;
		case "TAC":
			t=tipoStrumento.TAC;
			break;
		case "PET":
			t=tipoStrumento.PET;
			break;
		case "RM":
			t=tipoStrumento.RM;
			break;
		default:
			throw new DatoInputErrato("Tipo di strumento invalido:"+s);
		}
		return t;
	}
	
	public String getCodiceAnalisi() {
		return codiceAnalisi;
	}
	/*public void setCodiceAnalisi(String codiceAnalisi) {
		this.codiceAnalisi = codiceAnalisi;
	}*/
	public Integer getIdPersistenza() {
		return idPersistenza;
	}
	public void setIdPersistenza(Integer idPersistenza) {
		this.idPersistenza = idPersistenza;
	}
	public tipoStrumento getTipo(){
		return this.tipo;
	}

	private String codiceAnalisi;
	private Integer idPersistenza;
	private tipoStrumento tipo;

}
