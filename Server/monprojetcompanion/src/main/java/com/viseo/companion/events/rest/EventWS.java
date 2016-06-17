package com.viseo.companion.events.rest;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.companion.events.domain.Event;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import com.viseo.companion.events.dao.*;
@CrossOrigin
@RestController
public class EventWS {

	@Inject
	EventDAO eventDAO;

	@CrossOrigin
	@RequestMapping(value = "${endpoint.addEvent}", method = RequestMethod.POST)
    @ResponseBody
    public void addEvent(@Valid @RequestBody Event myEvent, BindingResult bindingResult){
			eventDAO.addEvent(myEvent);
    }
	
	@RequestMapping(value = "${endpoint.readEvent}", method = RequestMethod.GET)
	@ResponseBody
    public List<Event> ReadEvent(){	
		return eventDAO.GetAllEvent();
	}
}