package com.viseo.companion.evenements.dao;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
	public Event getEvent(long id){
		return em.find(Event.class, id);
	}

	@Transactional
	public void addEvent(String eventName,Date date,String description,String motclefs,String lieu){		
		Event event = new Event();		
		event.setDate(date);
		event.setDescription(description);
		event.setEvent(eventName);
		event.setLieu(lieu);
		event.setMotclefs(motclefs);
		addEvent(event);		
	}

	@Transactional
	public void addEvent(Event event){
		em.persist(event);
	}

	@Transactional
	public boolean isEventAlreadySaved(String eventName){	
		Query query=em.createQuery("select e from Event e where e.event=:eventName");
		query.setParameter("eventName",eventName);		
		@SuppressWarnings("unchecked")
		Collection<Event> list = (Collection<Event>) query.getResultList();
		return !list.isEmpty();
	}
	
	@Transactional
	public List<Event> getAllEvent() {		
		return em.createQuery("select a from Event a order by a.date", Event.class).getResultList();
	}
}
