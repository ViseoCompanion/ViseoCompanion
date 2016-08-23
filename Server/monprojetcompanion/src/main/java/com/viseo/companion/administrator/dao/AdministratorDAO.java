package com.viseo.companion.administrator.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.companion.administrator.domain.Administrator;

@Repository
public class AdministratorDAO {
	@PersistenceContext
	EntityManager em;
	BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	public Administrator getAdmin(long id){
		return em.find(Administrator.class,id);
	}
	
	@Transactional
	public void addAdmin(long id, String email, String password){	
		Administrator admin = new Administrator();
		admin.setEmail(email);
		admin.setPassword(password);
		addAdmin(admin);
	}
	
	@Transactional
	public Collection<Administrator> getAdminByEmail(String email){
		Query query=em.createQuery("select c from Administrator c where c.email=:email");
		query.setParameter("email",email);		
		@SuppressWarnings("unchecked")
		Collection<Administrator> list = (Collection<Administrator>) query.getResultList();
		return list;
	}
	
	@Transactional
	public Administrator getAdminIdByEmail(String email) {	
		Collection<Administrator> list=getAdminByEmail(email);
		return list.iterator().next();
	}
	
	@Transactional
	public void addAdmin(Administrator admin){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		em.persist(admin);
	}
	
	@Transactional
	public boolean isAdminAlreadySaved(String email){
		Collection<Administrator> list=getAdminByEmail(email);
		return !list.isEmpty();
	}
	
	@Transactional
	public boolean isAuthenticater(String email, String password){	
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Collection<Administrator> list=getAdminByEmail(email);
		return encoder.matches(password, list.iterator().next().getPassword());
	}
	
	@Transactional
	public List<Administrator> GetAllAdmin() {	
		return em.createQuery("select a from Administrator a", Administrator.class).getResultList();
	}
}
