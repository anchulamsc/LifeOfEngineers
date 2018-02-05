package com.loe.dms.spring.model.data;

import java.io.Serializable;

public class ContactInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String user_name;
	private String user_email;
	private String user_mobile;
	private String user_message;

	private String user_id;
	private String contact_id;
	private String employer;
	private String working_client;
	private String working_location;
	private String street;
	private String apt;
	private String city;
	private String state;
	private String postal_cd;
	private String country;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	public String getUser_message() {
		return user_message;
	}

	public void setUser_message(String user_message) {
		this.user_message = user_message;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getContact_id() {
		return contact_id;
	}

	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getWorking_client() {
		return working_client;
	}

	public void setWorking_client(String working_client) {
		this.working_client = working_client;
	}

	public String getWorking_location() {
		return working_location;
	}

	public void setWorking_location(String working_location) {
		this.working_location = working_location;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getApt() {
		return apt;
	}

	public void setApt(String apt) {
		this.apt = apt;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostal_cd() {
		return postal_cd;
	}

	public void setPostal_cd(String postal_cd) {
		this.postal_cd = postal_cd;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
