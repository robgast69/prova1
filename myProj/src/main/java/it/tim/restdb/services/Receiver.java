package it.tim.restdb.services;

import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import it.tim.restdb.configuration.JMSConfig;

@Service
public class Receiver {
    private final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

	@Autowired
    private JMSConfig jmsConfig;

	public CountDownLatch getLatch() {
      return latch;
    }

    @JmsListener(destination = "helloworld.q", containerFactory = "queueListenerFactory")
    public void receive(String message) {
    	logger.info("received message='{}'", message);
    	latch.countDown();
    }

}
