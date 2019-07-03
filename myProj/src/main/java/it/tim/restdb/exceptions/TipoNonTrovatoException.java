package it.tim.restdb.exceptions;

public class TipoNonTrovatoException extends RuntimeException {
	
	String msg;
	public TipoNonTrovatoException() {}
	public TipoNonTrovatoException(Long id) {
		this.msg = "Tipo con id "+id+" non trovato";
	}
	@Override
	public String getMessage() {
		return msg;
	}

}
