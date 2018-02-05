/**
 * Copyright 2010 Jee Vang 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0 
 *  
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  See the License for the specific language governing permissions and 
 *  limitations under the License. 
 */
package com.loe.dms.spring.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loe.dms.spring.model.data.ContactInfo;
import com.loe.dms.spring.model.data.EmailProperties;

/**
 * 
 * @author venkat
 *
 */
public class EmailSenderUtil {
	private static final Logger logger = LoggerFactory.getLogger(EmailSenderUtil.class);
	private static EmailProperties emailProperties = new EmailProperties();
	private static Session session = null;

	static {
		loadEmailProperties();
		try {
			session = getEmailSession();
		} catch (Exception e) {
			logger.error("Exception occurs while initializing the User Session in Static block" + e.getMessage());
		}
	}

	/*
	 * This method should read the details from properties file
	 */
	private static void loadEmailProperties() {
		logger.info(">>> loadEmailProperties >>>");
		emailProperties.setSmtpHost("smtp.gmail.com");
		emailProperties.setSmtpPort(587);
		emailProperties.setUserName("lifeofyoungengineers@gmail.com");
		emailProperties.setPassword("loe@2016");
		emailProperties.setSender("lifeofyoungengineers@gmail.com");
		emailProperties.setRecipient("lifeofyoungengineers2016@gmail.com");
		emailProperties.setMultipleRecipients("anchu.venki@gmail.com,kanagrjtcs@gmail.com");
	}

	private static Session getEmailSession() {
		logger.info(">>> getEmailSession >>>");
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", emailProperties.getSmtpHost());
		properties.put("mail.smtp.port", emailProperties.getSmtpPort());
		// Session session = Session.getDefaultInstance(properties, null);
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailProperties.getUserName(), emailProperties.getPassword());
			}
		});
		return session;
	}

	/**
	 * 
	 * @param mimeMessage
	 * @throws MessagingException
	 */
	private static void triggerEmailNotification(MimeMessage mimeMessage) throws MessagingException {
		logger.info(">>> triggerEmailNotification >>>");
		Transport.send(mimeMessage);
		logger.info(">>> Email Notification Successfully Sent >>>");

	}

	/**
	 * 
	 * @param messageSubject
	 * @param messageBody
	 * @return
	 * @throws MessagingException
	 */
	private static MimeMessage populateMimeMessage(String messageSubject, String messageBody, String sender, String receipent, String cclist) throws MessagingException {
		logger.info(">>> populateMimeMessage >>>");
		Session session = EmailSenderUtil.session;
		if (session == null) {
			session = getEmailSession();
		}
		MimeMessage mimeMessage = new MimeMessage(session);
		MimeMultipart mimeMultipart = new MimeMultipart();
		MimeBodyPart textBodyPart = new MimeBodyPart();

		InternetAddress iaSender = new InternetAddress(sender);
		InternetAddress iaRecipient = new InternetAddress(receipent);
		mimeMessage.setSender(iaSender);
		mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
		if (cclist != null && cclist != "") {
			mimeMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cclist));
		}
		mimeMessage.setSubject(messageSubject);

		textBodyPart.setText(messageBody);
		mimeMultipart.addBodyPart(textBodyPart);

		mimeMessage.setContent(mimeMultipart);

		return mimeMessage;

	}

	public static void sendMessage(ContactInfo contactInfo) {

		logger.info(">>> sendMessage >>>");

		String messageSubject = "Please Take Necessary Action For " + contactInfo.getUser_name();
		StringBuilder sb = new StringBuilder();

		sb.append("Hai Administrator Please review the message details sent by consumer and take the necessary action accordingly :");
		sb.append("\n");
		sb.append("\n");
		sb.append("Name :");
		sb.append(contactInfo.getUser_name());
		sb.append("\n");
		sb.append("Email Address :");
		sb.append(contactInfo.getUser_email());
		sb.append("\n");
		sb.append("Mobile No :");
		sb.append(contactInfo.getUser_mobile());
		sb.append("\n");
		sb.append("Message :");
		sb.append(contactInfo.getUser_message());
		sb.append("\n");

		String messageBody = sb.toString();

		try {
			MimeMessage mimeMessage = populateMimeMessage(messageSubject, messageBody, emailProperties.getSender(), emailProperties.getRecipient(), emailProperties.getMultipleRecipients());
			triggerEmailNotification(mimeMessage);

			sendReplyConfirmation(contactInfo);
		} catch (MessagingException e) {
			logger.error("Exception occured while sending email" + e);
		}

	}

	public static void sendReplyConfirmation(ContactInfo contactInfo) {
		logger.info(">>> sendReplyConfirmation >>>");
		String messageSubject = "Life Of Engineers Will Take Necessary Action For Your Request";
		StringBuilder sb = new StringBuilder();

		sb.append("Hai Welcome to Life of Engineers Application,Our Adminstrator will review your request and respond accordingly.Please be patient");
		sb.append("\n");
		sb.append("\n");
		sb.append("If you want immediate assitance please reach out to our admin @ ");
		sb.append(emailProperties.getRecipient());
		sb.append("\n");
		String messageBody = sb.toString();
		try {
			MimeMessage mimeMessage = populateMimeMessage(messageSubject, messageBody, emailProperties.getSender(), contactInfo.getUser_email(), "");
			triggerEmailNotification(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Exception occured while sending email" + e);
		}

	}

	public static boolean sendPasswordMessage(String emailId, String password) {

		logger.info(">>> sendPasswordMessage >>>");
		boolean emailSucess = false;
		String messageSubject = "Please Try Login with the Provided password ";
		StringBuilder sb = new StringBuilder();

		sb.append("Your System password is: " + password);

		sb.append("\n");

		String messageBody = sb.toString();

		try {
			MimeMessage mimeMessage = populateMimeMessage(messageSubject, messageBody, emailProperties.getSender(), emailId, "");
			triggerEmailNotification(mimeMessage);
			emailSucess = true;
		} catch (MessagingException e) {
			logger.error("Exception occured while sending email" + e);
		}

		return emailSucess;

	}

	public static boolean sendEmailVerificationMessage(String emailId, String userID, String url) {

		logger.info(">>> sendPasswordMessage >>>");

		boolean emailSucess = false;

		String messageSubject = "Please Verfiy your email by clicking the link below ";
		String activateUrl = url.replace("/enroll", "/verifyemail");
		activateUrl = activateUrl + "/" + userID;
		StringBuilder sb = new StringBuilder();

		sb.append("\n");
		sb.append("Click To Verify..  " + activateUrl);

		sb.append("\n");

		String messageBody = sb.toString();

		try {
			MimeMessage mimeMessage = populateMimeMessage(messageSubject, messageBody, emailProperties.getSender(), emailId, "");
			triggerEmailNotification(mimeMessage);
			emailSucess = true;
		} catch (MessagingException e) {
			logger.error("Exception occured while sending email" + e);
		}

		return emailSucess;

	}

}