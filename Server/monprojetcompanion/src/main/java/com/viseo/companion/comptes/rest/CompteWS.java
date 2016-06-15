package com.viseo.companion.comptes.rest;


import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.viseo.companion.comptes.dao.CompteDAO;
import com.viseo.companion.comptes.domain.Compte;
import com.viseo.companion.evenements.dao.*;
import com.viseo.companion.compteEvents.domain.*;;

@RestController
public class CompteWS {

	@Inject
	CompteDAO compteDAO;
	@Inject
	EventDAO eventDAO;

	@RequestMapping(value = "${endpoint.addCompte}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addCompte(@Valid @RequestBody Compte myCompte, BindingResult bindingResult){

		if(!(bindingResult.hasErrors()) && !compteDAO.isCompteAlreadySaved(myCompte.getEmail())){
			compteDAO.addCompte(myCompte);
			return true;
		}
		return false;
    }
	
	@RequestMapping(value = "${endpoint.addCompte}", method = RequestMethod.GET)
	@ResponseBody
    public List<Compte> ReadCompte(){	
		return compteDAO.getAllCompte();
	}
	
	@RequestMapping(value = "${endpoint.Authentification}", method = RequestMethod.POST)
	@ResponseBody
	public boolean Authentification(@Valid @RequestBody Compte myCompte, BindingResult bindingResult){

		if(!(bindingResult.hasErrors()) && compteDAO.isCompteAlreadySaved(myCompte.getEmail())
				&& compteDAO.isAuthenticater(myCompte.getEmail(), myCompte.getPassword())){
		return true;
		}	
		return false;
	}	
	
	@RequestMapping(value = "${endpoint.checkCompte}", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkCompte(@Valid @RequestBody Compte myCompte, BindingResult bindingResult){
		if(!(bindingResult.hasErrors()) && !compteDAO.isCompteAlreadySaved(myCompte.getEmail())){
			return true;
		}
		return false;
    }

	@RequestMapping(value = "${endpoint.getIdCompte}", method = RequestMethod.GET)
    @ResponseBody
    public Object findCompteByEmail(@PathVariable String email){
		return compteDAO.getCompteByEmail(email);
    }
	
	@RequestMapping(value = "${endpoint.participateEvent}", method = RequestMethod.GET)
    @ResponseBody
    public boolean participationToEvent(@PathVariable("idCompte") long idCompte,@PathVariable("idEvent") long idEvent, @PathVariable("participation") boolean participation){
			compteDAO.setParticipation(idCompte,idEvent,participation);
			return true;
    }

	@RequestMapping(value = "${endpoint.getParticipation}", method = RequestMethod.GET)
    @ResponseBody
    public boolean getParticipationCompteEvent(@PathVariable("idCompte") long idCompte,@PathVariable("idEvent") long idEvent){
		return compteDAO.getParticipation(idCompte,idEvent);
	}
	
	@RequestMapping(value = "${endpoint.doneParticipation}", method = RequestMethod.GET)
    @ResponseBody
    public boolean isAllreadySetParticipation(@PathVariable("idCompte") long idCompte,@PathVariable("idEvent") long idEvent){
		return compteDAO.isSetParticipation(idCompte,idEvent);
	}
}