package com.viseo.companion.evenements.dao;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.companion.evenements.domain.Event;


@Repository
public class EventDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Event getEvent(int id){
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
		q.select(c).where(cb.equal(c.get("event"), event));
		list = (Collection<Event>) em.createQuery(q).getResultList();
		return !list.isEmpty(); //return true if the list is not avoid
	}

	public List<Event> GetAllEvent() {		
		return em.createQuery("select a from Event a order by a.date", Event.class).getResultList();
	}
}
