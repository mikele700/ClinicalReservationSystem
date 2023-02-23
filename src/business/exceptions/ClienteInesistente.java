package business.exceptions;

public class ClienteInesistente extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3253701808649560320L;
	public ClienteInesistente(){
		
	}
	public String getMessage(){
		return message;
	}
	private final String message="Cliente inesistente.";

}
