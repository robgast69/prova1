package it.tim.restdb.test;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import it.tim.restdb.MyProjApplication;
import it.tim.restdb.controller.GreetingController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=it.tim.restdb.controller.GreetingController.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GreetingTests {
	Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @InjectMocks
    GreetingController greetingController;
    
    private static Logger log = LoggerFactory.getLogger(MyProjApplication.class);

    @Before
    public void setUp(){
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void test1Greet() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/greeting"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
}
