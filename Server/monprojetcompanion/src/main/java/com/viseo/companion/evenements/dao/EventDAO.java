package com.viseo.companion.evenements.dao;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.companion.evenements.domain.Event;


@Repository
public class EventDAO {

	@PersistenceContext
	EntityManager em;




	@Transactional
	public Event getEvent(long id){
		return em.find(Event.class, id);
	}

	@Transactional
	public void addEvent(String eventname,Date date,String description,String motclefs,String lieu){
		
		Event event = new Event();
		
		event.setDate(date);
		event.setDescription(description);
		event.setEvent(eventname);
		event.setLieu(lieu);
		event.setMotclefs(motclefs);
		em.persist(event);
		
	}

	@Transactional
	public void addEvent(Event event){
		em.persist(event);
	}


	public boolean isEventAlreadySaved(String event){

		Collection<Event> list = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();

		  CriteriaQuery<Event> q = cb.createQuery(Event.class);
		  Root<Event> c = q.from(Event.class);
		  //ParameterExpression<String> p = cb.parameter(String.class);
		  q.select(c).where(cb.equal(c.get("event"), event));

		  list = (Collection<Event>) em.createQuery(q).getResultList();

		return !list.isEmpty(); //return true if the list is not avoid
	}


	public List<Event> GetAllEvent() {
		
		return em.createQuery("select a from Event a order by a.date", Event.class).getResultList();
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
