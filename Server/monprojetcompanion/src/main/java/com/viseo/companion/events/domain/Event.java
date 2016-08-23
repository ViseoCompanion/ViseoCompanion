package com.viseo.companion.events.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.viseo.companion.accountEvents.domain.AccountEvent;

@SuppressWarnings("serial")
@Entity
public class Event implements java.io.Serializable
{
	@Id
	@GeneratedValue
	private long id;
	@Version
	private long version;
	private String event;
	private Date date;
	private String description;
	private String motclefs;
	private String lieu;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.event", cascade = CascadeType.ALL)
	private Set<AccountEvent> accountEvents = new HashSet<AccountEvent>();
	
	public Event() {
	}
	
	public Event(String event,Date date,String description,String motclefs,String lieu){
		this.event=event;
		this.date=date;
		this.description=description;
		this.motclefs=motclefs;
		this.lieu=lieu;
	}

	public long getId() {
		return id;
	}
	
	public String getEvent() {
		return this.event;
	}
	
	public Date getDate(){	
		return this.date;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getMotclefs(){
		return this.motclefs;
	}

	public String getLieu(){
		return this.lieu;
	}
	
	public void setEvent(String event){	
		this.event=event;
	}
	
	public void setDate(Date date){	
		this.date=date;
	}
	
	public void setDescription(String description){
		this.description=description;
	}
	
	public void setMotclefs(String motclefs){	
		this.motclefs=motclefs;
	}
	
	public void setLieu(String lieu){
		this.lieu=lieu;
	}

	@JsonBackReference
	public Set<AccountEvent> getAccountEvents(){
		return accountEvents;
	}
}
	
	
	
	
	

