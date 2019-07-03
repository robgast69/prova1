package it.tim.restdb.test;

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
import it.tim.restdb.controller.GreetingController;
import it.tim.restdb.controller.JPAController;
import it.tim.restdb.entities.Tipo;
import it.tim.restdb.repository.OggettiRepository;
import it.tim.restdb.repository.TipiRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JPATests {
	Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @InjectMocks
    GreetingController greetingController;
    @InjectMocks
    JPAController itemController;
    @Mock
    TipiRepository tipiRepository;
    @Mock
    OggettiRepository oggettiRepository;
    
    private static Logger log = LoggerFactory.getLogger(MyProjApplication.class);

    @Before
    public void setUp(){
    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void test2Tipi() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/tipi/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void test3TipiIns() throws Exception {
    	mvc.perform( MockMvcRequestBuilders
    		      .post("/tipi")
    		      .content(asJsonString(new Tipo(4L, "mobili")))
    		      .contentType(MediaType.APPLICATION_JSON)
    		      .accept(MediaType.APPLICATION_JSON))
    		      .andExpect(MockMvcResultMatchers.status().isCreated())
    		      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
    
    @Test
    public void test4Tipi() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/tipi/4").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void test5TipiUpd() throws Exception {
    	mvc.perform( MockMvcRequestBuilders
    		      .put("/tipi/{id}", 4)
    		      .content(asJsonString(new Tipo(4L, "immobili")))
    		      .contentType(MediaType.APPLICATION_JSON)
    		      .accept(MediaType.APPLICATION_JSON))
    		      .andExpect(MockMvcResultMatchers.status().isOk())
    		      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("4"))
    		      .andExpect(MockMvcResultMatchers.jsonPath("$.descrizione").value("immobili"));
    }
    
    @Test
    public void test6Tipi() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/tipi/4").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void test7TipiDel() throws Exception {
    	mvc.perform( MockMvcRequestBuilders.delete("/tipi/{id}", 4) )
        			.andExpect(MockMvcResultMatchers.status().isAccepted());
    }
    
    @Test
    public void test8Tipi() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/tipi/4").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
