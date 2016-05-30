package com.viseo.companion.evenements.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viseo.companion.comptes.*;
import com.viseo.companion.comptes.domain.Compte;


@Entity
public class Event 
{
	private int id;
	private String event;
	private Date date;
	private String description;
	private String motclefs;
	private String lieu;
	private Set<Compte> comptes;
	
//	public Event() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
	
	@Id
	@GeneratedValue
	@Column(name="EVENT_ID")
	public int getId() {
		return id;
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
	
	public void setId(int id){
		this.id=id;
		
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
	@JsonIgnore
	
	@ManyToMany(cascade=CascadeType.MERGE, mappedBy="events")
//	private List<Compte> compteList;
//	
//	public List<Compte> getComptes(){
//		return compteList;
//	}
//	public void setEvents(List<Compte> compteList){
//		this.compteList=compteList;
//	}
	public Set<Compte> getComptes(){
		return comptes;
	}
	public void setComptes(Set<Compte> comptes){
		this.comptes = comptes;
	}
	
	
}
	
	
	
	
	

