package it.tim.restdb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import it.tim.restdb.configuration.JMSConfig;

@Service
public class Publisher {

    private static final Logger logger = LoggerFactory.getLogger(Publisher.class);

    @Autowired
    private JmsTemplate jmsTemplate;

	@Autowired
    private JMSConfig jmsConfig;

    public void send(String message) {
    	logger.info("sending message='{}' on "+jmsConfig.topicName, message);
    	jmsTemplate.convertAndSend(jmsConfig.topicName, message);
    }
 
}
