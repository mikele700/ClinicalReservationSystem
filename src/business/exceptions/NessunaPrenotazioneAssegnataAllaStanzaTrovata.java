package business.exceptions;

public class NessunaPrenotazioneAssegnataAllaStanzaTrovata extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7131665441515976236L;
	public NessunaPrenotazioneAssegnataAllaStanzaTrovata(){
		
	}
	public String getMessage(){
		return message;
	}
	private final String message="Nessuna Prenotazione Assegnata trovata per la Stanza ricercata.";

}
