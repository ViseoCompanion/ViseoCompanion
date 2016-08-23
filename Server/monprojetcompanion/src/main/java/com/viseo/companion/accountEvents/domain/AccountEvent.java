package com.viseo.companion.accountEvents.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import com.viseo.companion.accounts.domain.Account;
import com.viseo.companion.events.domain.Event;

@SuppressWarnings("serial")
@Entity
@AssociationOverrides({
        @AssociationOverride(name = "pk.account",
            joinColumns = @JoinColumn()),
        @AssociationOverride(name = "pk.event",
            joinColumns = @JoinColumn())})
public class AccountEvent implements java.io.Serializable {
	private AccountEventID pk = new AccountEventID();
	private boolean participation;
	
	@EmbeddedId
	public AccountEventID getPk(){
		return pk;
	}
	
	@Transient
	public Account getAccount(){
		return getPk().getAccount();
	}
	
	public void setAccount(Account account){
		getPk().setAccount(account);
	}
	
	@Transient
	public Event getEvent(){
		return getPk().getEvent();
	}
	
	public void setEvent(Event event){
		getPk().setEvent(event);
	}
	
	public void setPk(AccountEventID pk){
		this.pk=pk;
	}
	
	public boolean isParticipated() {
		return participation;
	}
	
	public void setParticipated(boolean participation) {
		this.participation = participation;
	}
}
