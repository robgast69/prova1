package it.tim.restdb.entities;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListaMessaggi {
	Logger logger = LoggerFactory.getLogger(ListaMessaggi.class);
	
	private Messaggio[] lista;
	
	public ListaMessaggi() {
		lista = new Messaggio[0];
	}
	
	public ListaMessaggi(List<Object[]> lis) {
		lista = new Messaggio[lis.size()];
		int i = 0;
		for (Object[] ob : lis) {
			lista[i++] = new Messaggio((Long)ob[0],(String)ob[1],(Timestamp)ob[2]);
		}
	}

	public boolean isEmpty() {
		return lista.length==0;
	}
	
	public Messaggio[] getLista() {
		return lista;
	}
			
	@Override 
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[ListaMessaggi|"+lista.length+"|");
		for (int i=0; i< lista.length; i++) {
			sb.append(lista[i].toString());
		}
		sb.append("]");
		return sb.toString();
	}

}
