package it.tim.restdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import lombok.extern.slf4j.Slf4j;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories("it.tim.restdb.repository")
@EnableJpaAuditing
@EnableJms
@Slf4j
public class MyProjApplication{
    
	public static void main(String[] args) {
		SpringApplication.run(MyProjApplication.class, args);
	}

	public void registerBeans(ConfigurableApplicationContext context ){
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(JmsTemplate.class);
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		builder.addPropertyValue("connectionFactory", cachingConnectionFactory);      // set property value
		DefaultListableBeanFactory factory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
		factory.registerBeanDefinition("jmsTemplateName", builder.getBeanDefinition());
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	 }
}
