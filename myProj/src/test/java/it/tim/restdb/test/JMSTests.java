package it.tim.restdb.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.tim.restdb.MyProjApplication;
import it.tim.restdb.controller.JMSController;
import it.tim.restdb.services.Receiver;
import it.tim.restdb.repository.MessaggiRepository;
import it.tim.restdb.services.Sender;
import it.tim.restdb.services.Subscriber;
import it.tim.restdb.services.Publisher;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JMSTests {
	Logger logger = LoggerFactory.getLogger(JMSTests.class);
     
	@Autowired
	private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @InjectMocks
    JMSController itemController;
    @Mock
    MessaggiRepository messaggiRepository;
 
	@Autowired
    private Sender qSender;

	@Autowired
    private Publisher tSender;

    @Autowired
    private Receiver qReceiver;

    @Autowired
    private Subscriber tReceiver;

    @Before
    public void setUp(){
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void test0SendReceiveQueue() throws Exception {
      qSender.send("Hello Spring JMS ActiveMQ!");
      assert(true);
    }
    
    @Test
    public void test1SendReceiveQueue() throws Exception {
      logger.info("count before="+qReceiver.getLatch().getCount());
      qReceiver.getLatch().await(3000, TimeUnit.MILLISECONDS);
      logger.info("count after="+qReceiver.getLatch().getCount());
      assertThat(qReceiver.getLatch().getCount()).isEqualTo(0);
    }
    
    @Test
    public void test2SendTopic() throws Exception {
    	tSender.send("azione invio");
    	assert(true);
    }
    
    @Test
    public void test3memorizzaMess() throws Exception {
       	int prima = tReceiver.getRep().ricercaParziale("prova").size();
        logger.info("prima="+prima);
    	tReceiver.receive("messaggio di prova");
    	int dopo = tReceiver.getRep().ricercaParziale("prova").size();
        logger.info("dopo="+dopo);
     	assertThat(dopo).isEqualTo(prima+1);
   }

    @Test
    public void test4ReceiveTopic() throws Exception {
      logger.info("topic count before="+tReceiver.getLatch().getCount());
      tReceiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
      logger.info("topic count after="+tReceiver.getLatch().getCount());
      assertThat(tReceiver.getLatch().getCount()).isEqualTo(8);
    }

     @Test
     public void test9LetturaMess() throws Exception {
         this.mvc.perform(MockMvcRequestBuilders.get("/messaggi/zio").accept(MediaType.APPLICATION_JSON))
             .andExpect(MockMvcResultMatchers.status().isOk());
     }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
