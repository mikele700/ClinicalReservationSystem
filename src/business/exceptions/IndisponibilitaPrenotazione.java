package business.exceptions;

public class IndisponibilitaPrenotazione extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6879057087148127559L;

	public IndisponibilitaPrenotazione(){
		
	}
	public String getMessage(){
		return message;
	}
	private final String message="Prenotazione non disponibile.";

}
