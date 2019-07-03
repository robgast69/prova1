package it.tim.restdb.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.tim.restdb.configuration.JMSConfig;
import it.tim.restdb.entities.Greeting;

@RestController
public class GreetingController {
	Logger logger = LoggerFactory.getLogger(GreetingController.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
		   
    @RequestMapping("/greeting")
    @ResponseStatus(HttpStatus.OK)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
