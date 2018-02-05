/*
 * Copyright © Foremost Insurance 2010

 * All Rights Reserved
 *
 * Foremost NSS Application.
 *
 * Created:  Dec 16, 2010 3:08:29 PM
 * Author:   usw4w72
 * Project:  nssEJB
 *
 * $LastChangedBy$ usw4w72
 * $LastChangedRevision$
 * $LastChangedDate$ Dec 15, 2010 11:08:29 AM
 */
package com.loe.dms.spring.data.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loe.dms.spring.model.data.EventsInfo;
import com.loe.dms.spring.model.data.RegistrationInfo;
import com.loe.dms.spring.model.entity.ContactDetails;
import com.loe.dms.spring.model.entity.EventDetails;
import com.loe.dms.spring.model.entity.RegistrationDetails;
import com.loe.dms.spring.model.entity.Users;
import com.loe.dms.spring.util.DateUtil;

/**
 * 
 * @author venkat
 *
 */
public class DataTransformationUtil {

	private static final Logger logger = LoggerFactory.getLogger(DataTransformationUtil.class);

	public static Users prepareRegistrationUsersBusinessData(RegistrationInfo registrationInfo) {
		Users users = new Users();

		RegistrationDetails registrationDetails = new RegistrationDetails();
		List<ContactDetails> contactDetailsList = new ArrayList<ContactDetails>();
		ContactDetails contactDetails = new ContactDetails();

		users.setUser_name(registrationInfo.getUser_name());
		users.setPassword(registrationInfo.getPassword());
		users.setMobile_num(registrationInfo.getMobile_num());

		registrationDetails.setFirst_name(registrationInfo.getFirst_name());
		registrationDetails.setLast_name(registrationInfo.getLast_name());
		registrationDetails.setMiddle_name(registrationInfo.getMiddle_name());
		if (DateUtil.isValidDate(registrationInfo.getBirth_date())) {
			registrationDetails.setBirth_date(DateUtil.convertStringToDate(registrationInfo.getBirth_date()));
		}
		registrationDetails.setGender(registrationInfo.getGender());
		registrationDetails.setMarital_status(registrationInfo.getMarital_status());
		registrationDetails.setTechnologies(registrationInfo.getTechnologies());

		contactDetailsList.add(contactDetails);
		registrationDetails.setContactDetails(contactDetailsList);
		users.setRegistrationDetails(registrationDetails);
		return users;

	}

	public static Users prepareLoginUsersBusinessData(RegistrationInfo registrationInfo) {
		Users users = new Users();
		users.setUser_name(registrationInfo.getUser_name());
		users.setPassword(registrationInfo.getPassword());
		return users;

	}

	public static EventDetails prepareEventsBusinessData(EventsInfo eventsInfo) {
		EventDetails eventDetails = new EventDetails();
		eventDetails.setUser_id(eventsInfo.getUser_id());
		eventDetails.setEvent_name(eventsInfo.getEvent_name());
		if (DateUtil.isValidDate(eventsInfo.getEvent_date())) {
			eventDetails.setEvent_date(DateUtil.convertStringToDate(eventsInfo.getEvent_date()));
		}
		eventDetails.setEvent_details(eventsInfo.getEvent_details());
		return eventDetails;
	}

}