package com.viseo.companion.accounts.dao;

import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.companion.accounts.domain.Account;
import com.viseo.companion.accountEvents.domain.AccountEvent;
import com.viseo.companion.events.domain.Event;;

@Repository
public class AccountDAO {

	@PersistenceContext
	EntityManager em;
	BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	public Account getAccount(long id){
		return em.find(Account.class, id);
	}

	@Transactional
	public void addAccount(long id, String email, String password, String username){	
		Account account = new Account();
		account.setEmail(email);
		account.setPassword(password);
		addAccount(account);
	}
	
	public Collection<Account> getAccountByEmail(String email){
		Query query=em.createQuery("select c from Account c where c.email=:email");
		query.setParameter("email",email);		
		@SuppressWarnings("unchecked")
		Collection<Account> list = (Collection<Account>) query.getResultList();
		return list;
	}
	
	public Collection<AccountEvent> getAccountEventById(long idAccount,long idEvent){
		Query query=em.createQuery("select ce from AccountEvent ce where ce.pk.account=:account and ce.pk.event=:event");
		Account account=getAccount(idAccount);
		Event event=getEvent(idEvent);
		query.setParameter("account",account);	
		query.setParameter("event",event);
		@SuppressWarnings("unchecked")
		Collection<AccountEvent> list = (Collection<AccountEvent>) query.getResultList();
		return list;
	}
	
	@Transactional
	public Event getEvent(long id){
		return em.find(Event.class, id);
	}

	@Transactional
	public void addAccount(Account account){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		em.persist(account);
	}

	@Transactional
	public boolean isAccountAlreadySaved(String email){
		Collection<Account> list=getAccountByEmail(email);
		return !list.isEmpty();
	}
	
	@Transactional
	public boolean isAuthenticater(String email, String password){	
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Collection<Account> list=getAccountByEmail(email);
		return encoder.matches(password, list.iterator().next().getPassword());
	}
	
	@Transactional
	public List<Account> GetAllAccount() {	
		return em.createQuery("select a from Account a", Account.class).getResultList();
	}

	@Transactional
	public Account getAccountIdByEmail(String email) {	
		Collection<Account> list=getAccountByEmail(email);
		return list.iterator().next();
	}

	@Transactional
	public void participationEvent(long idAccount,long idEvent,boolean participation){
		AccountEvent accountEvent = new AccountEvent();	
		Account account=em.find(Account.class, idAccount);
		accountEvent.setAccount(account);	//set account in the join table
		Event event=em.find(Event.class, idEvent);
		accountEvent.setEvent(event);
		accountEvent.setParticipated(participation);	//set participate the event
		em.merge(accountEvent);
	}
	
	@Transactional
	public boolean getParticipation(long idAccount,long idEvent){
		Collection<AccountEvent> list = getAccountEventById(idAccount,idEvent);
		return list.iterator().next().isParticipated();
	}
	
	@Transactional
	public boolean isSetParticipation(long idAccount,long idEvent){
		Collection<AccountEvent> list = getAccountEventById(idAccount,idEvent);
		return !list.isEmpty();
	}
}
