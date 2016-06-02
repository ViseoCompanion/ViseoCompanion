package com.viseo.companion.comptes.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	public Compte getCompte(int id){
		return em.find(Compte.class, id);
	}

	@Transactional
	public void addCompte(int id, String email, String password, String username){
		
		Compte compte = new Compte();
		compte.setEmail(email);
		compte.setPassword(password);
		em.persist(compte);
		
	}
	@Transactional
	public Event getEvent(int id){
		return em.find(Event.class, id);
	}

	@Transactional
	public void addCompte(Compte compte){
		em.persist(compte);
	}

	@Transactional
	public boolean isCompteAlreadySaved(String myCompte){

		Collection<Compte> list = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();

		  CriteriaQuery<Compte> q = cb.createQuery(Compte.class);
		  Root<Compte> c = q.from(Compte.class);
		  //ParameterExpression<String> p = cb.parameter(String.class);
		  q.select(c).where(cb.equal(c.get("email"), myCompte));

		  list = (Collection<Compte>) em.createQuery(q).getResultList();

		return !list.isEmpty(); //return true if the list is not avoid
	}
	@Transactional
	public boolean isAuthenticater(String Email, String Password){
		
		Collection<Compte> list = null;
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Compte> cq = builder.createQuery(Compte.class);
		//select *from
		Root r = cq.from(Compte.class);
		cq.select(r);
		//where
		Predicate usereq = builder.equal(r.get("email"),Email);
		Predicate pwdeq = builder.equal(r.get("password"), Password);
		Predicate predicate = builder.and(usereq,pwdeq);
		cq.where(predicate);
		//execution
		list=(Collection<Compte>) em.createQuery(cq).getResultList();
		
		return !list.isEmpty();
		
	}
	@Transactional
	public List<Compte> GetAllCompte() {
		
		return em.createQuery("select a from Compte a", Compte.class).getResultList();
	}

	@Transactional
	public Object GetCompteByEmail(String emailDemande) {
		
		return em.createQuery("SELECT id FROM Compte WHERE email LIKE :emailCompte")
				.setParameter("emailCompte", emailDemande)
				.getResultList().get(0);
	}

	@Transactional
	public void addEventToCompte(int idCompte,int idEvent){
		CompteEvent compteEvent = new CompteEvent();
		
		Compte compte=new Compte();
		compte.setId(idCompte);
		compteEvent.setCompte(compte);
		
		Event event=new Event();
		event.setId(idEvent);
		compteEvent.setEvent(event);
		
		compteEvent.setParticipated(true);
		
		compte.addCompteEvent(compteEvent);
		
		em.merge(compteEvent);
	}
	
	@Transactional
	public void cancelEventFromCompte(int idCompte,int idEvent){
		CompteEvent compteEvent = new CompteEvent();
		
		Compte compte=new Compte();
		compte.setId(idCompte);
		compteEvent.setCompte(compte);
		
		Event event=new Event();
		event.setId(idEvent);
		compteEvent.setEvent(event);
		
		compteEvent.setParticipated(false);
		
		compte.addCompteEvent(compteEvent);
		
		em.merge(compteEvent);
	}
	
	@Transactional
	public boolean getParticipation(int idCompte,int idEvent){
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
		return result.iterator().next().isParticipated();
	}
}
