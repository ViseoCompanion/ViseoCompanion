package com.viseo.companion.evenements.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.viseo.companion.comptes.domain.Compte;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.viseo.companion.compteEvents.domain.CompteEvent;


@Entity
public class Event implements java.io.Serializable
{
	@Id
	@GeneratedValue
	private long idEvent;
	@Version
	private long version;
	private String event;
	private Date date;
	private String description;
	private String motclefs;
	private String lieu;

	private Set<CompteEvent> compteEvents = new HashSet<CompteEvent>();
	
	public Event() {
	}
	
	public Event(String event,Date date,String description,String motclefs,String lieu){
		this.event=event;
		this.date=date;
		this.description=description;
		this.motclefs=motclefs;
		this.lieu=lieu;
	}
	
	
	@Id
	@GeneratedValue
	@Column(name="EVENT_ID")
	public long getId() {
		return idEvent;
	}
	
	@Column(name="EVENT_NAME")
	public String getEvent() {
		return this.event;
	}
	
	@Column(name="DATE")
	public Date getDate(){
		
		return this.date;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription(){
		return description;
	}
	
	@Column(name="MOT_CLEFS")
	public String getMotclefs(){
		return this.motclefs;
	}
	@Column(name="LIEU")
	public String getLieu(){
		return this.lieu;
	}
	
	public void setId(long id){
		this.idEvent=id;
		
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.event", cascade = CascadeType.ALL)
	@JsonBackReference
	public Set<CompteEvent> getCompteEvents(){
		return compteEvents;
	}
	public void setCompteEvents(Set<CompteEvent> events){
		this.compteEvents = events;
	}
}
	
	
	
	
	

