package com.viseo.companion.events.dao;

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

import com.viseo.companion.events.domain.Event;


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
	
	public Collection<Event> getEventByNameDate(String eventname,long date){
		Query query=em.createQuery("select e from Event e where e.event=:event and e.date=:date");
		Date date1 = new Date(date);
		query.setParameter("event",eventname);
		query.setParameter("date",date1);	
		@SuppressWarnings("unchecked")
		Collection<Event> list = (Collection<Event>) query.getResultList();
		return list;
	}
	
	@Transactional
	public void updateEvent(String eventnameO,long dateO,Event eventT){		
		Collection<Event> list=getEventByNameDate(eventnameO,dateO);
		Event event=getEvent(list.iterator().next().getId());
		em.remove(event);
		em.persist(eventT);
	}
	
	@Transactional
	public void deleteEvent(String eventname,long date){		
		Collection<Event> list=getEventByNameDate(eventname,date);
		Event event=getEvent(list.iterator().next().getId());
		em.remove(event);
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

	public List<Event> getAllEvent() {		
		return em.createQuery("select a from Event a order by a.date", Event.class).getResultList();
	}
}
