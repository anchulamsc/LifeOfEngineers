package com.loe.dms.spring.service;

import java.util.List;

import com.loe.dms.spring.model.data.ServiceResponse;
import com.loe.dms.spring.model.entity.EventDetails;

public interface EventsService {

	public ServiceResponse addEvent(EventDetails eventDetails);

	public ServiceResponse updateEvent(EventDetails eventDetails);

	public ServiceResponse deleteEventById(String user_id, String event_id);

	public List<EventDetails> listEventDetails(String user_id);

}
