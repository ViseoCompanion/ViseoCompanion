package com.viseo.companion.comptes.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.companion.comptes.domain.Compte;
import com.viseo.companion.evenements.domain.*;


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
	
//	@Transactional
//	public void addEventToCompte(long id, Set<Event> event){
//		Compte compte=getCompte(id);
//		compte.setEvents(event);
//		em.persist(compte);
//		
//	}
	
	@Transactional
	public void addEventToCompte(int id,Event myEvent){
		Compte compte=getCompte(id);
		compte.getEvents().add(myEvent);
	}
	
	@Transactional
	public void deleteEventFromCompte(int id,Event myEvent){
		Compte compte=getCompte(id);
		compte.getEvents().remove(myEvent);
	}

	@Transactional
	public void addCompte(Compte compte){
		em.persist(compte);
	}


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
		/*Compte compte =em.createQuery(cq).getSingleResult();*/
		list=(Collection<Compte>) em.createQuery(cq).getResultList();
		
		return !list.isEmpty();
		
	}

	public List<Compte> GetAllCompte() {
		
		return em.createQuery("select a from Compte a", Compte.class).getResultList();
	}

	public Compte getCompteByEmail(String email) {
		return em.find(Compte.class, email);
		
	}
	public Object GetCompteByEmail(String emailDemande) {
		
		return em.createQuery("SELECT id FROM Compte WHERE email LIKE :emailCompte")
				.setParameter("emailCompte", emailDemande)
				.getResultList().get(0);
	}

/*	public boolean isThereOneSessionFormationAlreadyPlanned(SessionFormation sf){

		Collection<SessionFormation> list = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();

		  CriteriaQuery<SessionFormation> q = cb.createQuery(SessionFormation.class);
		  Root<SessionFormation> c = q.from(SessionFormation.class);
		  //ParameterExpression<String> p = cb.parameter(String.class);

		  q.select(c).where(cb.equal(c.get("formation"), sf.getFormation().getId()),
				  cb.or(
					  cb.and(
						  cb.greaterThanOrEqualTo(c.<Date>get("debut"), sf.getDebut()),
						  cb.lessThan(c.<Date>get("debut"), sf.getFin())
						  ),
					  cb.and(
							  cb.greaterThan(c.<Date>get("fin"), sf.getDebut()),
							  cb.lessThanOrEqualTo(c.<Date>get("fin"), sf.getFin())
						  ),
					  cb.and(
							  cb.lessThanOrEqualTo(c.<Date>get("debut"), sf.getDebut()),
							  cb.greaterThanOrEqualTo(c.<Date>get("fin"), sf.getFin())
						  )
					  )
		  );

		  list = (Collection<SessionFormation>) em.createQuery(q).getResultList();

		return !list.isEmpty();
	}

	public boolean hasCorrectDates(SessionFormation mySessionFormation){
		return mySessionFormation.getDebut().before(mySessionFormation.getFin());
	}*/
}
