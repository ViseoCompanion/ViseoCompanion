package com.viseo.companion.accountEvents.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.viseo.companion.accounts.domain.Account;
import com.viseo.companion.events.domain.Event;

@SuppressWarnings("serial")
@Embeddable
public class AccountEventID implements java.io.Serializable{
	private Account account;
	private Event event;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Account getAccount(){
		return account;
	}
	
	public void setAccount(Account account){
		this.account=account;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Event getEvent(){
		return event;
	}
	
	public void setEvent(Event event){
		this.event=event;
	}
}
