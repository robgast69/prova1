package it.tim.restdb.exceptions;

public class OggettoNonTrovatoException extends RuntimeException {
	
	String msg;
	public OggettoNonTrovatoException() {}
	public OggettoNonTrovatoException(Long id) {
		this.msg = "Oggetto con id "+id+" non trovato";
	}
	@Override
	public String getMessage() {
		return msg;
	}

}
