package business.exceptions;

public class NessunClienteTrovato extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5101159014200332751L;
	public NessunClienteTrovato() {
	}
	public String getMessage(){
		return message;
	}
	private final String message="Nessun cliente trovato.";
}
