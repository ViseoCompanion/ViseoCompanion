package com.viseo.companion.comptes.rest;

import java.lang.reflect.Field;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.companion.evenements.domain.Event;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import com.viseo.companion.comptes.dao.CompteDAO;
import com.viseo.companion.comptes.domain.Compte;
import com.viseo.companion.evenements.dao.*;


@RestController
public class CompteWS {

	@Inject
	CompteDAO compteDAO;
	@Inject
	EventDAO eventDAO;

	
	@RequestMapping(value = "${endpoint.helloworld2}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addCompte(@Valid @RequestBody Compte myCompte, BindingResult bindingResult){

		if(!(bindingResult.hasErrors()) && !compteDAO.isCompteAlreadySaved(myCompte.getEmail())){
			compteDAO.addCompte(myCompte);
			return true;
		}
		return false;
    }
	

		
	@RequestMapping(value = "${endpoint.helloworld2}", method = RequestMethod.GET)
	@ResponseBody
    public List<Compte> ReadCompte(){	
		return compteDAO.GetAllCompte();
	}
	
	@RequestMapping(value = "${endpoint.helloworld3}", method = RequestMethod.POST)
	@ResponseBody
	public boolean Authentification(@Valid @RequestBody Compte myCompte, BindingResult bindingResult){

		if(!(bindingResult.hasErrors()) && compteDAO.isCompteAlreadySaved(myCompte.getEmail())
				&& compteDAO.isAuthenticater(myCompte.getEmail(), myCompte.getPassword())){
		return true;
		}	
		return false;
	}	
	
	@RequestMapping(value = "${endpoint.helloworld4}", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkCompte(@Valid @RequestBody Compte myCompte, BindingResult bindingResult){
		if(!(bindingResult.hasErrors()) && !compteDAO.isCompteAlreadySaved(myCompte.getEmail())){
			return true;
		}
		return false;
    }
	
//	@RequestMapping(value = "${endpoint.helloworld5}", method = RequestMethod.POST)
//    @ResponseBody
//    public void addEventToCompte(@Valid @RequestBody Compte myCompte,@Valid @RequestBody Set<Event> myEvent, BindingResult bindingResult){
//			compteDAO.addEventToCompte(myCompte.getId(), myEvent);
//    }
	@RequestMapping(value = "${endpoint.helloworld5}", method = RequestMethod.POST)
    @ResponseBody
    public void addToEventCompte(@PathVariable int id,@Valid @RequestBody Event myEvent, BindingResult bindingResult){
			compteDAO.addEventToCompte(id,myEvent);
    }
	
	@RequestMapping(value = "${endpoint.helloworld7}", method = RequestMethod.GET)
    @ResponseBody
    public Object findCompteByEmail(@PathVariable String email){
		return compteDAO.GetCompteByEmail(email);
    }
	
	@RequestMapping(value = "${endpoint.helloworld8}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteEventCompte(@PathVariable int id,@Valid @RequestBody Event myEvent, BindingResult bindingResult){
			compteDAO.deleteEventFromCompte(id,myEvent);
    }
}