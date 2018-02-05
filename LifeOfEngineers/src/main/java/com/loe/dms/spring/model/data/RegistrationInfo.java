package com.loe.dms.spring.model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegistrationInfo extends ServiceResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String user_id;
	private String user_name;
	private String password;
	private String confirm_password;
	private String mobile_num;
	private String first_name;
	private String last_name;
	private String middle_name;
	private String birth_date;
	private String gender;
	private String marital_status;
	private String technologies;
	private List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();

	private String role;
	private String active_flag;
	private String serviceError;
	private String infoMessage;

	private boolean loginview;
	private boolean forgotview;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public String getMobile_num() {
		return mobile_num;
	}

	public void setMobile_num(String mobile_num) {
		this.mobile_num = mobile_num;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getTechnologies() {
		return technologies;
	}

	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}

	public List<ContactInfo> getContactInfos() {
		return contactInfos;
	}

	public void setContactInfos(List<ContactInfo> contactInfos) {
		this.contactInfos = contactInfos;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getActive_flag() {
		return active_flag;
	}

	public void setActive_flag(String active_flag) {
		this.active_flag = active_flag;
	}

	public String getServiceError() {
		return serviceError;
	}

	public void setServiceError(String serviceError) {
		this.serviceError = serviceError;
	}

	public String getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}

	public boolean isLoginview() {
		return loginview;
	}

	public void setLoginview(boolean loginview) {
		this.loginview = loginview;
	}

	public boolean isForgotview() {
		return forgotview;
	}

	public void setForgotview(boolean forgotview) {
		this.forgotview = forgotview;
	}
}
