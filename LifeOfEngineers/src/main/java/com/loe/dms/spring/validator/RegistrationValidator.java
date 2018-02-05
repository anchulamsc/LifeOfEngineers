package com.loe.dms.spring.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.loe.dms.spring.model.data.ErrorInfoData;
import com.loe.dms.spring.model.data.RegistrationInfo;
import com.loe.dms.spring.util.DateUtil;

@Component
public class RegistrationValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationValidator.class);

	public boolean supports(Class clazz) {
		return RegistrationInfo.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		logger.info(">>>validate>>>");

		RegistrationInfo registrationInfo = (RegistrationInfo) target;
		String emailId = registrationInfo.getUser_name();
		String mobileNum= registrationInfo.getMobile_num();
		String password = registrationInfo.getPassword();
		String confPassword = registrationInfo.getConfirm_password();
		String birthDate = registrationInfo.getBirth_date();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_name", "error.user_name", "Email id must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirm_password", "error.confirm_password", "Password must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile_num", "error.mobile_num", "Mobile number must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name", "error.first_name", "First Name must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "error.last_name", "Last Name must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birth_date", "error.birth_date", "Valid Birth Date must be entered.");

		if (!emailId.isEmpty() && !emailId.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			errors.rejectValue("user_name", "", "Please enter valid Email Id");
		}

		if (!password.isEmpty() && !confPassword.isEmpty() && !password.equals(confPassword)) {
			errors.rejectValue("confirm_password", "", "Password and Confirm Password do not match.");
		}

		if (!password.isEmpty() && (password.length() < 5 || password.length() > 15)) {
			errors.rejectValue("password", "", "Password should be minimum of 5 and maximum of 15 characters.");
		}
		
		if (!mobileNum.isEmpty() && !mobileNum.matches("[0-9()-\\.]*")) {
			errors.rejectValue("mobile_num", "", "Please enter valid Mobile Number");
		}

		if (!birthDate.isEmpty() && !DateUtil.isValidDate(birthDate)) {
			errors.rejectValue("birth_date", "", "Date of Birth Entered Invalid,Please enter valid date.");
		}

	}

	public void validateServiceErrors(Object target, Errors errors) {
		logger.info(">>>validateServiceErrors>>>");
		RegistrationInfo registrationInfo = (RegistrationInfo) target;
		ErrorInfoData errorInfoData = registrationInfo.getErrorInfoData();
		if (errorInfoData != null && errorInfoData.hasErrors()) {
			for (String errormessage : errorInfoData.getErrorInfos()) {
				errors.rejectValue("serviceError", "", errormessage);
			}
		}

	}

	public void validateLogin(Object target, Errors errors) {
		logger.info(">>>validateLogin>>>");
		RegistrationInfo registrationInfo = (RegistrationInfo) target;
		String emailId = registrationInfo.getUser_name();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_name", "error.user_name", "Email id must be entered.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password must be entered.");

		if (!emailId.isEmpty() && !emailId.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			errors.rejectValue("user_name", "", "Please enter valid Email Id");
		}
	}

	public void validateEmailId(Object target, Errors errors) {
		logger.info(">>>validateEmailId>>>");
		RegistrationInfo registrationInfo = (RegistrationInfo) target;
		String emailId = registrationInfo.getUser_name();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_name", "error.user_name", "Email id must be entered.");

		if (!emailId.isEmpty() && !emailId.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			errors.rejectValue("user_name", "", "Please enter valid Email Id");
		}
	}

}