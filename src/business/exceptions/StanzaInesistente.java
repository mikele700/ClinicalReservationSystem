package business.exceptions;

public class StanzaInesistente extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7403878275480419540L;
	
	public StanzaInesistente(){
		
	}
	public String getMessage(){
		return message;
	}
	private final String message="Stanza inesistente.";


}
