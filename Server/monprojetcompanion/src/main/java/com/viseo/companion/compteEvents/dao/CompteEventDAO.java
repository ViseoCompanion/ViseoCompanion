package com.viseo.companion.compteEvents.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.viseo.companion.comptes.domain.Compte;
import com.viseo.companion.evenements.domain.Event;
import com.viseo.companion.compteEvents.domain.CompteEvent;

public class CompteEventDAO {
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Compte getCompte(int id){
		return em.find(Compte.class, id);
	}
	
	@Transactional
	public Event getEvent(int id){
		return em.find(Event.class, id);
	}
	
	@Transactional
	public void addEventToCompte(int idCompte,int idEvent){
		Compte compte=getCompte(idCompte);
		Event event=getEvent(idEvent);
		CompteEvent compteEvent = new CompteEvent();
		compteEvent.setCompte(compte);
		compteEvent.setEvent(event);
		compteEvent.setParticipated(true);
		compte.getCompteEvents().add(compteEvent);
		//em.persist(compteEvent);
	}
	
	@Transactional
	public void deleteEventFromCompte(int id,Event myEvent){
		Compte compte=getCompte(id);
		CompteEvent compteEvent = new CompteEvent();
		compteEvent.setCompte(compte);
		compteEvent.setEvent(myEvent);
		compteEvent.setParticipated(false);
		em.persist(compteEvent);
	}
}
