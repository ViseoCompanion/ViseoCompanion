package com.viseo.companion.comptes.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viseo.companion.evenements.*;
import com.viseo.companion.evenements.domain.Event;

@Entity
public class Compte{
	@Id
	@GeneratedValue
	@Column(name = "COMPTE_ID")
	private int id;
	private String email;
	private String password;
	private Set<Event> events;
	
//	public Compte() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
	@Id
	@GeneratedValue
	@Column(name = "COMPTE_ID")
	public int getId() {
		return id;
	}
	
	@NotNull
	@Column(name = "EMAIL")
	public String getEmail(){
		return email;
	}
	
	@NotNull
	@Column(name = "PASSWORD")
	public String getPassword(){
		return password;
	}
	
	public void setId(int id){
		this.id=id;
		
	}
	public void setEmail(String email){
		
		this.email=email;
	}

	public void setPassword(String password){
		this.password=password;
	}
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "COMPTE_EVENT", joinColumns = { @JoinColumn(name = "COMPTE_ID") }, inverseJoinColumns = { @JoinColumn(name = "EVENT_ID") })
//	private List<Event> eventList;
//	
//	public List<Event> getEvents(){
//		return eventList;
//	}
//	public void setEvents(List<Event> eventList){
//		this.eventList=eventList;
//	}
	
	public Set<Event> getEvents(){
		return events;
	}
	public void setEvents(Set<Event> events){
		this.events = events;
	}
	
	
}
	
	
	
	
	

