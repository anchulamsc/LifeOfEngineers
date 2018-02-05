package com.loe.dms.spring.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loe.dms.spring.model.data.EventsInfo;
import com.loe.dms.spring.util.DateUtil;

@Component
public class EventsInfoValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(EventsInfoValidator.class);

	public boolean supports(Class clazz) {
		return EventsInfo.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		logger.info(">>>validate>>>");

		EventsInfo eventsInfo = (EventsInfo) target;
		String eventDate = eventsInfo.getEvent_date();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_date", "error.event_date", "Event date must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_name", "error.event_name", "Event name must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_details", "error.event_details", "Event details must be entered.");

		if (!eventDate.isEmpty() && !DateUtil.isValidDate(eventDate)) {
			errors.rejectValue("birth_date", "", "Event Date Entered Invalid,Please enter valid date.");
		}
	}

}