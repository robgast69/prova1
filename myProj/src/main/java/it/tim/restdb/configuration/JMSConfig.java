package it.tim.restdb.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import it.tim.restdb.controller.JMSController;

@Configuration
@PropertySource("classpath:application.properties")
public class JMSConfig implements InitializingBean {
	static Logger logger = LoggerFactory.getLogger(JMSConfig.class);
	
	@Value("${msg.queue}")
	public String queueName;

	@Value("${msg.topic}")
	public String topicName;

	@Override
	public void afterPropertiesSet() throws Exception  {
	      logger.info("queueName="+queueName);
	      logger.info("topicName="+topicName);
	}
}
