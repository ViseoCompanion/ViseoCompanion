package com.viseo.companion.comptes.domain;

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
import com.viseo.companion.evenements.domain.Event;
import com.viseo.companion.compteEvents.domain.CompteEvent;

@Entity
@Table(name = "compte")
public class Compte implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	private int idCompte;
	private String email;
	private String password;
	
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
	
	@Id
	@GeneratedValue
	@Column(name = "COMPTE_ID")
	public int getId() {
		return idCompte;
	}
	
	@Column(name = "EMAIL")
	public String getEmail(){
		return email;
	}
	
	@Column(name = "PASSWORD")
	public String getPassword(){
		return password;
	}
	
	public void setId(int id){
		this.idCompte=id;
		
	}
	public void setEmail(String email){
		
		this.email=email;
	}

	public void setPassword(String password){
		this.password=password;
	}

	@OneToMany(mappedBy = "pk.compte", cascade=CascadeType.ALL)
	public Set<CompteEvent> getCompteEvents(){
		return compteEvents;
	}

	public void setCompteEvents(Set<CompteEvent> compteEvents){
		this.compteEvents = compteEvents;
	}
	
	public void addCompteEvent(CompteEvent compteEvent){
		this.compteEvents.add(compteEvent);
	}
}
	
	
	
	
	

