package com.viseo.companion.accounts.rest;


import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.viseo.companion.accounts.dao.AccountDAO;
import com.viseo.companion.accounts.domain.Account;
import com.viseo.companion.events.dao.*;;

@RestController
public class AccountWS {

	@Inject
	AccountDAO accountDAO;
	@Inject
	EventDAO eventDAO;

	@RequestMapping(value = "${endpoint.addAccount}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addAccount(@Valid @RequestBody Account myAccount, BindingResult bindingResult){

		if(!(bindingResult.hasErrors()) && !accountDAO.isAccountAlreadySaved(myAccount.getEmail())){
			accountDAO.addAccount(myAccount);
			return true;
		}
		return false;
    }
	
	@RequestMapping(value = "${endpoint.addAccount}", method = RequestMethod.GET)
	@ResponseBody
    public List<Account> ReadAccount(){	
		return accountDAO.GetAllAccount();
	}
	
	@RequestMapping(value = "${endpoint.Authentification}", method = RequestMethod.POST)
	@ResponseBody
	public boolean Authentification(@Valid @RequestBody Account myAccount, BindingResult bindingResult){

		if(!(bindingResult.hasErrors()) && accountDAO.isAccountAlreadySaved(myAccount.getEmail())
				&& accountDAO.isAuthenticater(myAccount.getEmail(), myAccount.getPassword())){
		return true;
		}	
		return false;
	}	
	
	@RequestMapping(value = "${endpoint.checkAccount}", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkAccount(@Valid @RequestBody Account myAccount, BindingResult bindingResult){
		if(!(bindingResult.hasErrors()) && !accountDAO.isAccountAlreadySaved(myAccount.getEmail())){
			return true;
		}
		return false;
    }

	@RequestMapping(value = "${endpoint.getIdAccount}", method = RequestMethod.GET)
    @ResponseBody
    public long findAccountByEmail(@PathVariable String email){
		return accountDAO.getAccountIdByEmail(email).getId();
    }
	
	@RequestMapping(value = "${endpoint.participationEvent}", method = RequestMethod.GET)
    @ResponseBody
    public boolean addToEventAccount(@PathVariable("idAccount") long idAccount,@PathVariable("idEvent") long idEvent,@PathVariable("participation") boolean participation){
			accountDAO.participationEvent(idAccount,idEvent,participation);
			return true;
    }
	
	@RequestMapping(value = "${endpoint.getParticipation}", method = RequestMethod.GET)
    @ResponseBody
    public boolean getParticipationAccountEvent(@PathVariable("idAccount") long idAccount,@PathVariable("idEvent") long idEvent){
		return accountDAO.getParticipation(idAccount,idEvent);
	}
	
	@RequestMapping(value = "${endpoint.doneParticipation}", method = RequestMethod.GET)
    @ResponseBody
    public boolean isAllreadySetParticipation(@PathVariable("idAccount") long idAccount,@PathVariable("idEvent") long idEvent){
		return accountDAO.isSetParticipation(idAccount,idEvent);
	}
}