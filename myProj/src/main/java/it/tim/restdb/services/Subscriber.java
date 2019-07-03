package it.tim.restdb.services;

import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import it.tim.restdb.entities.Messaggio;
import it.tim.restdb.repository.MessaggiRepository;

@Service
public class Subscriber {
    private final Logger logger = LoggerFactory.getLogger(Subscriber.class);

    private CountDownLatch latch = new CountDownLatch(10);
    
    @Autowired
    private MessaggiRepository messaggiRepository;

    public CountDownLatch getLatch() {
      return latch;
    }

    @JmsListener(destination = "provaMyProj", containerFactory = "topicListenerFactory")
    public void receive(String message) {
    	logger.info("received message='{}'", message);
    	latch.countDown();
    	Messaggio m = new Messaggio();
    	m.setTesto(message);
    	m.setArrivo(new Timestamp(System.currentTimeMillis()));
    	messaggiRepository.save(m);
    }
    
    //serve x test
    public MessaggiRepository getRep() {return messaggiRepository;}
  
}
