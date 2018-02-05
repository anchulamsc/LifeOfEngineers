package com.loe.dms.spring.dao;

import java.util.List;

import com.loe.dms.spring.model.entity.EventDetails;

public interface EventsDAO {

	public void addEvent(EventDetails eventDetails);

	public void updateEvent(EventDetails eventDetails);

	public void deleteEventById(String user_id, String event_id);

	public List<EventDetails> getEventDetailsById(String user_id);

}
