package it.tim.restdb.entities;

import javax.persistence.Table;
import javax.persistence.TableGenerator;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name ="Messaggi")
//@Getter @Setter
public class Messaggio {
	
	 @Id
	 @GeneratedValue
	 //@GeneratedValue(strategy=GenerationType.TABLE, generator = "msg_generator")
	 //@TableGenerator(name="msg_generator", table="Messaggi", schema="mydb")
	 private Long id;
	 private String testo;
	 private Timestamp arrivo;	
	 
	 public Messaggio() {}
	 public Messaggio(Long id,String testo,Timestamp arrivo) {
		 this.id = id;
		 this.testo = testo; 
		 this.arrivo = arrivo;
	 }
	 
	 public Long getId() {return id;}
	 public String getTesto() {return testo;}
	 public Timestamp getArrivo() {return arrivo;}
	 public void setTesto(String tes) {testo=tes;}
	 public void setArrivo(Timestamp ar) {arrivo=ar;}
	
	 @Override
	 public String toString() {return "[Oggetto=|"+id+"|"+testo+"|"+arrivo+"]";}

}
