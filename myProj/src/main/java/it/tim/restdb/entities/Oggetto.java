package it.tim.restdb.entities;

import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.factory.annotation.*;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name ="Oggetti")
@NamedQuery(name="Oggetto.contaPerTipo", query="SELECT COUNT(o.valore) FROM Oggetto o WHERE o.tipo.id=:tipo") 
//@Getter @Setter
public class Oggetto {
	
	 @Id
	 @GeneratedValue//(strategy=GenerationType.TABLE)
	 private Long id;
	 private String descr;
	 private Integer valore;	
	 @ManyToOne
	 @JoinColumn(name="Tipo")
	 Tipo tipo;
	 
	 public Long getId() {return id;}
	 public String getDescr() {return descr;}
	 public Tipo getTipo() {return tipo;}
	 public Integer getValore() {return valore;}
	
	 @Override
	 public String toString() {return "[Oggetto=|"+id+"|"+descr+"|"+tipo+"|"+valore+"]";}

}
