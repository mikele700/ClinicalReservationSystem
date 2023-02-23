package business.exceptions;

public class DatoInputErrato extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4381617440497722798L;
	public DatoInputErrato() {
	}
	public DatoInputErrato(String specifica){
		this.message=this.message+": "+specifica;
	}
	public String getMessage(){
		return message;
	}
	private String message="Dato di input errato";

}
