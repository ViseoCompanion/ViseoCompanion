package com.viseo.companion.compteEvents.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.viseo.companion.comptes.domain.Compte;
import com.viseo.companion.evenements.domain.Event;

@Entity
@Table(name = "COMPTE_EVENTS")
@AssociationOverrides({
        @AssociationOverride(name = "pk.compte",
            joinColumns = @JoinColumn(name = "COMPTE_ID")),
        @AssociationOverride(name = "pk.event",
            joinColumns = @JoinColumn(name = "EVENT_ID")) })
public class CompteEvent implements java.io.Serializable {
	
	private CompteEventID pk = new CompteEventID();
	private boolean participation;
	
	@EmbeddedId
	public CompteEventID getPk(){
		return pk;
	}
	
	@Transient
	public Compte getCompte(){
		return getPk().getCompte();
	}
	public void setCompte(Compte compte){
		getPk().setCompte(compte);
	}
	
	@Transient
	public Event getEvent(){
		return getPk().getEvent();
	}
	public void setEvent(Event event){
		getPk().setEvent(event);
	}
	
	public void setPk(CompteEventID pk){
		this.pk=pk;
	}
	
	@Column(name="participation")
	public boolean isParticipated() {
		return participation;
	}
	public void setParticipated(boolean participation) {
		this.participation = participation;
	}
}
