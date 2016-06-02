package com.viseo.companion.compteEvents.rest;

import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.viseo.companion.evenements.domain.Event;
import com.viseo.companion.compteEvents.dao.CompteEventDAO;
import com.viseo.companion.comptes.dao.CompteDAO;
import com.viseo.companion.evenements.dao.*;

public class CompteEventWS {

	@Inject
	CompteEventDAO compteEventDAO;
	
	

	
	@RequestMapping(value = "${endpoint.helloworld8}", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteEventCompte(@PathVariable int id,@Valid @RequestBody Event myEvent, BindingResult bindingResult){
		compteEventDAO.deleteEventFromCompte(id,myEvent);
		return true;
    }
}
