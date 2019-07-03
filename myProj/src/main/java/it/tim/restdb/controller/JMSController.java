package it.tim.restdb.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.tim.restdb.entities.ListaMessaggi;
import it.tim.restdb.exceptions.MessaggioNonTrovatoException;
import it.tim.restdb.repository.MessaggiRepository;
import it.tim.restdb.services.Sender;
import it.tim.restdb.services.Publisher;

@RestController 
public class JMSController {
	Logger logger = LoggerFactory.getLogger(JMSController.class);

	@Autowired
    private MessaggiRepository messaggiRepository;
	 
    @Autowired
    private Sender qSender;
    
    @Autowired
    private Publisher tSender;

    @GetMapping("/messaggi/{testo}")
    public ListaMessaggi getMessaggi(@PathVariable String testo) {
    	List<Object[]> messaggi = messaggiRepository.ricercaParziale(testo);
    	ListaMessaggi response = new ListaMessaggi(messaggi); 
     	if (response.isEmpty())
    		throw new MessaggioNonTrovatoException(testo);
    	logger.info("media="+response);
        return response;
    }

    @PostMapping(value = "/send")
    public void sendMessageToAMSQueue(@RequestParam("message") String message) {
        this.qSender.send(message);
    }

    @PostMapping(value = "/publish")
    public void sendMessageToAMSTopic(@RequestParam("message") String message) {
        this.tSender.send(message);
    }

}
