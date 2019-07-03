package it.tim.restdb.repository;

import java.util.List;

import it.tim.restdb.entities.Messaggio;

public interface RicercaParzialeRepository {

	List<Object[]> ricercaParziale(String testo);

}
