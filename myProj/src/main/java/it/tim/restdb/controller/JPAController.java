package it.tim.restdb.controller;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.tim.restdb.entities.Oggetto;
import it.tim.restdb.entities.Tipo;
import it.tim.restdb.exceptions.OggettoNonTrovatoException;
import it.tim.restdb.exceptions.TipoNonTrovatoException;
import it.tim.restdb.repository.OggettiRepository;
import it.tim.restdb.repository.TipiRepository;

import org.springframework.http.HttpStatus; 

@RestController
public class JPAController {
	Logger logger = LoggerFactory.getLogger(JPAController.class);

    @Autowired
    private TipiRepository tipiRepository;
     
    @Autowired
    private OggettiRepository oggettiRepository;
     
    @PersistenceContext
    private EntityManager em;

    @GetMapping("/oggetti/{id}")
    public Oggetto getOggetti(@PathVariable Long id) {
    	Optional<Oggetto> oggetti = oggettiRepository.findById(id);
    	if (!oggetti.isPresent())
    		throw new OggettoNonTrovatoException(id);
    	Oggetto response = oggetti.orElse(null);
    	logger.info("tipo="+response);
        return response;
    }

    @GetMapping("/contaOggetti/{tipo}")
    public Long getOggettiCount(@PathVariable Long tipo) {
    	TypedQuery<Long> query = em.createNamedQuery("Oggetto.contaPerTipo", Long.class);
    	query.setParameter("tipo", tipo);
    	Long response = query.getResultList().get(0);
    	logger.info("conto="+response);
        return response;
    }

    @GetMapping("/tipi/{id}")
    public Tipo getTipi(@PathVariable Long id) {
    	if (id<=0) {
         	throw new TipoNonTrovatoException(id);
    	}
     	Optional<Tipo> tipi = tipiRepository.findById(id);
     	if (!tipi.isPresent())
    		throw new TipoNonTrovatoException(id);
    	Tipo response = tipi.orElse(new Tipo((long)0,"fail"));
    	logger.info("tipo="+response);
        return response;
    }

    @PostMapping(value ="/tipi" ,
    			 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},  
    			 consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    Tipo newTipo(@RequestBody Tipo newTipo) {
    	Tipo response =tipiRepository.save(newTipo);
    	logger.info("inserted "+response);
        return response;
    }
    
    @PutMapping("/tipi/{id}")
    Tipo replaceTipo(@RequestBody Tipo newTipo, @PathVariable Long id) {
/*     return tipiRepository.findById(id)
        .map(tipo -> {
        	tipo.setId(newTipo.getId());
        	tipo.setDescrizione(newTipo.getDescrizione());
        	return tipiRepository.save(tipo);
        })
        .orElseGet(() -> {
        	newTipo.setId(id);
        	return tipiRepository.save(newTipo);
        });*/
     	Optional<Tipo> tipi = tipiRepository.findById(id);
     	if (!tipi.isPresent())
    		throw new TipoNonTrovatoException(id);
    	newTipo.setId(id);
    	logger.info("updated "+newTipo);
    	return tipiRepository.save(newTipo);
    }

    @DeleteMapping("/tipi/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteTipo(@PathVariable Long id) {
    	try {
    	tipiRepository.deleteById(id);
    	}
    	catch (EmptyResultDataAccessException e) {
    		throw new TipoNonTrovatoException(id);
    	}
    	logger.info("deleted id "+id);
    }
    
    @GetMapping("/media/{id}")
    public Integer getMedia(@PathVariable Long id) {
    	Integer media = tipiRepository.averagePrice(id);
    	logger.info("media="+media);
        return media;
    }

}
