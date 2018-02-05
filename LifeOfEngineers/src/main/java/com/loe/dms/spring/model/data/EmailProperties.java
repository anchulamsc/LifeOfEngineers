package com.loe.dms.spring.model.data;

import java.io.Serializable;

public class EmailProperties implements Serializable {

	private static final long serialVersionUID = 1L;

	private String smtpHost; // replace this with a valid host
	private int smtpPort; // replace this with a valid port
	private String userName;
	private String password;
	String sender; // replace with a valid sender email address
	String recipient;
	String multipleRecipients;// Will be used as cc list to send

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getMultipleRecipients() {
		return multipleRecipients;
	}

	public void setMultipleRecipients(String multipleRecipients) {
		this.multipleRecipients = multipleRecipients;
	}

}
