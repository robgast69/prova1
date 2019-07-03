package it.tim.restdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.tim.restdb.entities.Messaggio;

public interface MessaggiRepository extends JpaRepository<Messaggio,Long>, RicercaParzialeRepository {
    @Query(value="SELECT id,testo,arrivo FROM Messaggio m WHERE m.testo like '%'||?1||'%'")
    public List<Object[]> ricercaParziale(String testo); 
}
