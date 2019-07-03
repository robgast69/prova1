package it.tim.restdb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.tim.restdb.entities.Messaggio;
import it.tim.restdb.entities.Tipo;

public interface TipiRepository extends CrudRepository<Tipo,Long>, AveragePriceRepository {
	   @Query(value="SELECT SUM(o.valore)/COUNT(o.valore) FROM Tipo t INNER JOIN Oggetto o on t.id=o.tipo.id WHERE t.id=?1")
		public Integer averagePrice(Long id); 
}
