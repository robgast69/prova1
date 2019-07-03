package it.tim.restdb.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.tim.restdb.controller.GreetingController;

@ControllerAdvice
public class VariousAdvices {
	  @ResponseBody
	  @ExceptionHandler(OggettoNonTrovatoException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String oggettoNotFoundHandler(OggettoNonTrovatoException ex) {
	    return ex.getMessage();
	  }

	  @ResponseBody
	  @ExceptionHandler(TipoNonTrovatoException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String tipoNotFoundHandler(TipoNonTrovatoException ex) {
	    return ex.getMessage();
	  }

	  @ResponseBody
	  @ExceptionHandler(MessaggioNonTrovatoException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String messaggioNotFoundHandler(MessaggioNonTrovatoException ex) {
	    return ex.getMessage();
	  }
}
