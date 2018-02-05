package com.loe.dms.spring.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loe.dms.spring.model.data.ContactInfo;

@Component
public class ContactInfoValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(ContactInfoValidator.class);

	public boolean supports(Class clazz) {
		return ContactInfo.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		logger.info(">>>validate>>>");

		ContactInfo contactInfo = (ContactInfo) target;
		String emailId = contactInfo.getUser_email();
		String mobileNum = contactInfo.getUser_mobile();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_name", "error.user_name", "Your name must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_email", "error.user_email", "Your email must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_mobile", "error.user_mobile", "Your Mobile No must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_message", "error.user_message", "Message Details must be entered.");

		if (!emailId.isEmpty() && !emailId.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			errors.rejectValue("user_email", "", "Please enter valid Email Id");
		}

		if (!mobileNum.isEmpty() && !mobileNum.matches("[0-9()-\\.]*")) {
			errors.rejectValue("user_mobile", "", "Please enter valid Mobile Number");
		}
	}

}