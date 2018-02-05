package com.loe.dms.spring.model.data;

import java.io.Serializable;

public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user_id = "";
	private String user_name = "";
	private String role = "";
	private String activeUser = "N";

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(String activeUser) {
		this.activeUser = activeUser;
	}

}
