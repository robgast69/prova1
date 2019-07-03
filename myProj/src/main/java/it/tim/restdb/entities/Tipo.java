package it.tim.restdb.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="Tipi")
//@Getter @Setter
public class Tipo {

	 @Id
	 private Long id;
	 
	 private String descrizione;
	 
	 protected Tipo() {};
	 
	 public Tipo(Long i,String d) {
		 id = i;
		 descrizione = d;
	 };
	 
	 public Long getId() {return id;}
	 public String getDescrizione() {return descrizione;}
	 public void setId(Long i) {id=i;}
	 public void setDescrizione(String d) {descrizione=d;}

	 @Override
	 public String toString() {return "[Tipo=|"+id+"|"+descrizione+"]";}
}
