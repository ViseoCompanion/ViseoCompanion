package com.viseo.companion.accounts.domain;

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
public class Account implements java.io.Serializable{

	@Id
	@GeneratedValue
	private long id;
	@Version
	private long version;
	private String email;
	private String password;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.account", cascade=CascadeType.ALL)
	private Set<AccountEvent> accountEvents = new HashSet<AccountEvent>();
	
	public Account() {
	}
	
	public Account(String email,String password){
		this.email = email;
		this.password = password;
	}
	
	public void addEvent(AccountEvent event){
		this.accountEvents.add(event);
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
	public Set<AccountEvent> getAccountEvents(){
		return accountEvents;
	}
}
	
	
	
	
	

