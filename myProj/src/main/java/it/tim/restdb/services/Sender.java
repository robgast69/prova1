package it.tim.restdb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import it.tim.restdb.configuration.JMSConfig;

@Service
public class Sender {

    private static final Logger logger = LoggerFactory.getLogger(Sender.class);
	
	@Autowired
    private JmsTemplate jmsTemplate;

	@Autowired
    private JMSConfig jmsConfig;

    public void send(String message) {
    	logger.info("sending message='{}' on "+jmsConfig.queueName, message);
    	jmsTemplate.convertAndSend("helloworld.q", message);
    }
 
}
