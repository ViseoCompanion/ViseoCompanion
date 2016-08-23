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
import org.springframework.web.bind.annotation.PathVariable;
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
    public boolean addEvent(@Valid @RequestBody Event myEvent, BindingResult bindingResult){
			if(!(bindingResult.hasErrors())){
				eventDAO.addEvent(myEvent);
				return true;
			}
			return false;
    }
	
	@RequestMapping(value = "${endpoint.readEvent}", method = RequestMethod.GET)
	@ResponseBody
    public List<Event> ReadEvent(){	
		return eventDAO.getAllEvent();
	}
	
	@RequestMapping(value = "${endpoint.removeEvent}", method = RequestMethod.POST)
	@ResponseBody
	public boolean removeEvent(@PathVariable("event") String event,@PathVariable("date") Long date){
			eventDAO.deleteEvent(event,date);
		return true;
	}
	
	@RequestMapping(value = "${endpoint.updateEvent}", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateEvent(@PathVariable("event") String event,@PathVariable("date") Long date,@Valid @RequestBody Event myEvent){
			eventDAO.updateEvent(event,date,myEvent);
		return true;
	}
}