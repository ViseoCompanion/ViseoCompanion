package com.viseo.companion.administrator.rest;

import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.viseo.companion.administrator.dao.AdministratorDAO;
import com.viseo.companion.administrator.domain.Administrator;
@CrossOrigin
@RestController
public class AdministratorWS {
	@Inject
	AdministratorDAO adminDAO;
	
	@CrossOrigin
	@RequestMapping(value = "${endpoint.addAdmin}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addAccount(@Valid @RequestBody Administrator myAdmin, BindingResult bindingResult){
		if(!(bindingResult.hasErrors()) && !adminDAO.isAdminAlreadySaved(myAdmin.getEmail())){
			adminDAO.addAdmin(myAdmin);
			return true;
		}
		return false;
    }
	
	@CrossOrigin
	@RequestMapping(value = "${endpoint.adminAuthentification}", method = RequestMethod.POST)
	@ResponseBody
	public boolean authentification(@Valid @RequestBody Administrator myAdmin, BindingResult bindingResult){
		if(!(bindingResult.hasErrors()) && adminDAO.isAdminAlreadySaved(myAdmin.getEmail())
				&& adminDAO.isAuthenticater(myAdmin.getEmail(), myAdmin.getPassword())){
		return true;
		}	
		return false;
	}
	
	@CrossOrigin
	@RequestMapping(value = "${endpoint.checkAdmin}", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkAccount(@Valid @RequestBody Administrator myAdmin, BindingResult bindingResult){
		if(!(bindingResult.hasErrors()) && !adminDAO.isAdminAlreadySaved(myAdmin.getEmail())){
			return true;
		}
		return false;
    }
	
	@CrossOrigin
	@RequestMapping(value = "${endpoint.getIdAdmin}", method = RequestMethod.GET)
    @ResponseBody
    public long findAdminByEmail(@PathVariable String email){
		return adminDAO.getAdminIdByEmail(email).getId();
    }
}
