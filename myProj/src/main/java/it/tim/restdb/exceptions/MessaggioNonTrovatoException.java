package it.tim.restdb.exceptions;

public class MessaggioNonTrovatoException extends RuntimeException {
	
	String msg;
	public MessaggioNonTrovatoException() {}
	public MessaggioNonTrovatoException(String testo) {
		this.msg = "Nessun messaggio contiene '"+testo+"'";
	}
	@Override
	public String getMessage() {
		return msg;
	}

}
