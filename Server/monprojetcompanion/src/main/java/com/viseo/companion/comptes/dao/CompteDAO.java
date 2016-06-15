package com.viseo.companion.comptes.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.mapping.Set;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.viseo.companion.comptes.domain.Compte;
import com.viseo.companion.compteEvents.domain.CompteEvent;
import com.viseo.companion.compteEvents.domain.CompteEventID;
import com.viseo.companion.evenements.domain.Event;;


@Repository
public class CompteDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Compte getCompte(long id){
		return em.find(Compte.class, id);
	}

	@Transactional
	public void addCompte(String email, String password, String username){	
		Compte compte = new Compte();
		compte.setEmail(email);
		compte.setPassword(password);
		addCompte(compte);
	}
	
	@Transactional
	public Event getEvent(long id){
		return em.find(Event.class, id);
	}

	@Transactional
	public void addCompte(Compte compte){
		em.persist(compte);
	}

	@Transactional
	public boolean isCompteAlreadySaved(String email){
		Query query=em.createQuery("select c from Compte c where c.email=:email");
		query.setParameter("email",email);		
		@SuppressWarnings("unchecked")
		Collection<Compte> list = (Collection<Compte>) query.getResultList();
		return !list.isEmpty();
	}
	
	@Transactional
	public boolean isAuthenticater(String email, String password){	
		Query query=em.createQuery("select c from Compte c where c.email=:email and c.password=:password");
		query.setParameter("email",email);	
		query.setParameter("password",password);
		@SuppressWarnings("unchecked")
		Collection<Compte> list = (Collection<Compte>) query.getResultList();
		return !list.isEmpty();	
	}
	
	@Transactional
	public List<Compte> getAllCompte() {	
		return em.createQuery("select a from Compte a", Compte.class).getResultList();
	}

	@Transactional
	public Object getCompteByEmail(String emailDemande) {	
		return em.createQuery("SELECT id FROM Compte WHERE email LIKE :emailCompte")
				.setParameter("emailCompte", emailDemande)
				.getResultList().get(0);
	}
	//get the generated id of the compte by email

	@Transactional
	public void setParticipation(long idCompte,long idEvent, boolean participation){
		
		CompteEvent compteEvent = new CompteEvent();	
		Compte compte=em.find(Compte.class, idCompte);
		compteEvent.setCompte(compte);	//set compte in the join table
		Event event=em.find(Event.class, idEvent);
		compteEvent.setEvent(event);
		System.out.print(participation);//set event	in the join table
		compteEvent.setParticipated(participation);	//set participate the event
		compte.addCompteEvent(compteEvent); 
		em.merge(compteEvent);
	}
	
	
	@Transactional
	public boolean getParticipation(long idCompte,long idEvent){
		java.util.Set<CompteEvent> result = new HashSet<CompteEvent>();//creat a new Set
		Compte compte=new Compte();		
		Event event=new Event();
		compte = getCompte(idCompte);
		java.util.Set<CompteEvent> compteEvent1 = compte.getCompteEvents();
		//put all the events that one compte participates into a Set
		event = getEvent(idEvent);
		java.util.Set<CompteEvent> compteEvent2 = event.getCompteEvents();
		//put all the comptes that participate one event into a Set
		result.clear();
		result.addAll(compteEvent1);
		//put all the elements in the first Set into the empty Set
		result.retainAll(compteEvent2);
		//Intersection of two Sets in order to get the one and only one object
		return result.iterator().next().isParticipated();
	}
	
	@Transactional
	public boolean isSetParticipation(long idCompte,long idEvent){
		java.util.Set<CompteEvent> result = new HashSet<CompteEvent>();
		Compte compte=new Compte();		
		Event event=new Event();
		compte = getCompte(idCompte);
		java.util.Set<CompteEvent> compteEvent1 = compte.getCompteEvents();
		event = getEvent(idEvent);
		java.util.Set<CompteEvent> compteEvent2 = event.getCompteEvents();
		result.clear();
		result.addAll(compteEvent1);
		result.retainAll(compteEvent2);
		return result.isEmpty();
		//do the same thing as the function "Getparticipation" just to return if the user has allready set the participation
	}
}
