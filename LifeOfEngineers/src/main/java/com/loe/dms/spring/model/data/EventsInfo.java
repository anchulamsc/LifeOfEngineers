package com.loe.dms.spring.model.data;

import java.io.Serializable;

public class EventsInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user_id;
	private String event_id;
	private String event_date;
	private String event_name;
	private String event_details;

	private boolean addView;
	private boolean displayView;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_details() {
		return event_details;
	}

	public void setEvent_details(String event_details) {
		this.event_details = event_details;
	}

	public boolean isAddView() {
		return addView;
	}

	public void setAddView(boolean addView) {
		this.addView = addView;
	}

	public boolean isDisplayView() {
		return displayView;
	}

	public void setDisplayView(boolean displayView) {
		this.displayView = displayView;
	}

}
