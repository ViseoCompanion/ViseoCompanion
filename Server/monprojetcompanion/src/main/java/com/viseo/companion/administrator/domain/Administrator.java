package com.viseo.companion.administrator.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Entity
public class Administrator implements java.io.Serializable{
	@Id
	@GeneratedValue
	private long id;
	@Version
	private long version;
	private String email;
	private String password;
	
	public Administrator(){
	}
	
	public Administrator(String email,String password){
		this.email = email;
		this.password = password;
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
}
