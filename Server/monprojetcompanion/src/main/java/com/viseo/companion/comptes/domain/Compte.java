package com.viseo.companion.comptes.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.viseo.companion.compteEvents.domain.CompteEvent;

@SuppressWarnings("serial")
@Entity
public class Compte implements java.io.Serializable{

	@Id
	@GeneratedValue
	private long id;
	@Version
	private long version;
	private String email;
	private String password;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.compte", cascade=CascadeType.ALL)
	private Set<CompteEvent> compteEvents = new HashSet<CompteEvent>();
	
	public Compte() {
	}
	
	public Compte(String email,String password){
		this.email = email;
		this.password = password;
	}
	
	public void addEvent(CompteEvent event){
		this.compteEvents.add(event);
	}
	
	public long getId() {
		return id;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setEmail(String email){
		this.email=email;
	}

	public void setPassword(String password){
		this.password=password;
	}

	@JsonBackReference
	public Set<CompteEvent> getCompteEvents(){
		return compteEvents;
	}
	
	public void addCompteEvent(CompteEvent compteEvent){
		this.compteEvents.add(compteEvent);
	}
}
	
	
	
	
	

