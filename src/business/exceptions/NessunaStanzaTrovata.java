package business.exceptions;

public class NessunaStanzaTrovata extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7290815667339182068L;
	public NessunaStanzaTrovata() {
	}
	public String getMessage(){
		return message;
	}
	private final String message="Nessuna stanza trovata.";
}
