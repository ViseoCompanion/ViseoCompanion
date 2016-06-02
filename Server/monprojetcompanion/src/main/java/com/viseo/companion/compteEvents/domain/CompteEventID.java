package com.viseo.companion.compteEvents.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.viseo.companion.comptes.domain.Compte;
import com.viseo.companion.evenements.domain.Event;

@Embeddable
public class CompteEventID implements java.io.Serializable{
	private Compte compte;
	private Event event;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Compte getCompte(){
		return compte;
	}
	public void setCompte(Compte compte){
		this.compte=compte;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Event getEvent(){
		return event;
	}
	public void setEvent(Event event){
		this.event=event;
	}
}
