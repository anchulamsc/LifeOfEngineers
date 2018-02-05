package com.loe.dms.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.loe.dms.spring.dao.EventsDAO;
import com.loe.dms.spring.model.data.ServiceResponse;
import com.loe.dms.spring.model.entity.EventDetails;
import com.loe.dms.spring.util.ApplicationConstants;
import com.loe.dms.spring.util.DateUtil;
import com.loe.dms.spring.util.RandomNumGenUtil;

@Service
public class EventsServiceImpl implements EventsService {

	private static final Logger logger = LoggerFactory.getLogger(EventsServiceImpl.class);

	private EventsDAO eventsDAO;

	@Autowired(required = true)
	@Qualifier(value = "eventsDAO")
	public void setEventsDAO(EventsDAO eventsDAO) {
		this.eventsDAO = eventsDAO;
	}

	public ServiceResponse addEvent(EventDetails eventDetails) {
		ServiceResponse serviceResponse = new ServiceResponse();
		eventDetails.setEvent_id(RandomNumGenUtil.generate4DigitRandomNum());
		eventDetails.setSequence_num(ApplicationConstants.DEFAULT_SEQ_NUMBER);
		eventDetails.setInserted_timestamp(DateUtil.getCurrentTime());
		eventDetails.setUpdated_timestamp(DateUtil.getCurrentTime());
		eventsDAO.addEvent(eventDetails);
		return serviceResponse;
	}

	public ServiceResponse updateEvent(EventDetails eventDetails) {
		ServiceResponse serviceResponse = new ServiceResponse();
		eventsDAO.updateEvent(eventDetails);
		return serviceResponse;
	}

	public ServiceResponse deleteEventById(String user_id, String event_id) {
		ServiceResponse serviceResponse = new ServiceResponse();
		eventsDAO.deleteEventById(user_id, event_id);
		return serviceResponse;
	}

	public List<EventDetails> listEventDetails(String user_id) {
		return eventsDAO.getEventDetailsById(user_id);
	}

}
