package com.viseo.companion.evenements.rest;

import java.lang.reflect.Field;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.companion.evenements.dao.*;
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