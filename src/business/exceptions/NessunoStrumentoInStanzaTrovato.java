package business.exceptions;

public class NessunoStrumentoInStanzaTrovato extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3626243531567749676L;
	public NessunoStrumentoInStanzaTrovato(){
		
	}
	public String getMessage(){
		return message;
	}
	private final String message="Nessuno Strumento trovato per la Stanza ricercata.";

}
