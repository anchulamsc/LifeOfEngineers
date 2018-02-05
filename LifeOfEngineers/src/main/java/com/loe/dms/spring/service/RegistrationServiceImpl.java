package com.loe.dms.spring.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.loe.dms.spring.dao.RegistrationDAO;
import com.loe.dms.spring.model.data.ErrorInfoData;
import com.loe.dms.spring.model.data.ServiceResponse;
import com.loe.dms.spring.model.entity.UserRoles;
import com.loe.dms.spring.model.entity.Users;
import com.loe.dms.spring.util.ApplicationConstants;
import com.loe.dms.spring.util.DateUtil;
import com.loe.dms.spring.util.RandomNumGenUtil;
import com.loe.dms.spring.util.SecurityUtil;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	private RegistrationDAO registrationDAO;

	@Autowired(required = true)
	@Qualifier(value = "registrationDAO")
	public void setRegistrationDAO(RegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}

	@Transactional
	public ServiceResponse enrollRegistrationDetails(Users users) {
		ServiceResponse serviceResponse = new ServiceResponse();
		String user_id = RandomNumGenUtil.generate10DigitRandomNum();
		users.setUser_id(user_id);
		String encryptedPassword = SecurityUtil.encrypt(users.getPassword());
		if (encryptedPassword != null && !encryptedPassword.isEmpty()) {
			users.setPassword(encryptedPassword);
		}
		users.setSequence_num(ApplicationConstants.DEFAULT_SEQ_NUMBER);
		users.setInserted_timestamp(DateUtil.getCurrentTime());
		users.setUpdated_timestamp(DateUtil.getCurrentTime());
		UserRoles userRoles = new UserRoles();
		userRoles.setUser_name(users.getUser_name());
		userRoles.setUser_id(user_id);
		userRoles.setEmail_verified("N");
		userRoles.setActive_flag("Y");
		userRoles.setRole("Admin");
		userRoles.setSequence_num(ApplicationConstants.DEFAULT_SEQ_NUMBER);
		userRoles.setInserted_timestamp(DateUtil.getCurrentTime());
		userRoles.setUpdated_timestamp(DateUtil.getCurrentTime());
		users.setUserRoles(userRoles);
		users.getRegistrationDetails().setUser_id(user_id);
		users.getRegistrationDetails().setSequence_num(ApplicationConstants.DEFAULT_SEQ_NUMBER);
		users.getRegistrationDetails().setInserted_timestamp(DateUtil.getCurrentTime());
		users.getRegistrationDetails().setUpdated_timestamp(DateUtil.getCurrentTime());

		Users retrievedUser = registrationDAO.performUserLogin(users);
		if (retrievedUser == null) {
			registrationDAO.enrollRegistrationDetails(users);
		} else {
			ErrorInfoData errorInfoData = new ErrorInfoData();
			errorInfoData.addErrorInfo("Entered email already registered in system,please try logging in or register with new email.");
			serviceResponse.setErrorInfoData(errorInfoData);
		}

		return serviceResponse;

	}

	public Users performUserLogin(Users users) {
		return registrationDAO.performUserLogin(users);
	}

	public ServiceResponse emailVerification(String user_id, String verificationCode) {
		ServiceResponse serviceResponse = new ServiceResponse();
		UserRoles userRoles = registrationDAO.emailVerification(user_id, verificationCode);
		ErrorInfoData errorInfoData = new ErrorInfoData();
		if (userRoles != null && !"N".equalsIgnoreCase(userRoles.getEmail_verified())) {
			errorInfoData.addErrorInfo("Email Verified Successfully,please try logging in..");
		} else if (userRoles == null) {
			errorInfoData.addErrorInfo("Verification is not Successfull,please contact admin.");
		}
		serviceResponse.setErrorInfoData(errorInfoData);
		return serviceResponse;
	}
}
