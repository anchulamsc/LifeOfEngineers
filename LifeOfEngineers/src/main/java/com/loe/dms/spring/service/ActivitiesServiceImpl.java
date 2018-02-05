package com.loe.dms.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.loe.dms.spring.dao.ActivitiesDAO;
import com.loe.dms.spring.model.entity.Users;

@Service
public class ActivitiesServiceImpl implements ActivitiesService {
	private static final Logger logger = LoggerFactory.getLogger(ActivitiesServiceImpl.class);

	private ActivitiesDAO activitiesDAO;

	@Autowired(required = true)
	@Qualifier(value = "activitiesDAO")
	public void setActivitiesDAO(ActivitiesDAO activitiesDAO) {
		this.activitiesDAO = activitiesDAO;
	}

	public void updateRegistrationDetails(Users users) {
		// TODO Auto-generated method stub

	}

	public List<Users> listRegisteredUserDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Users> listActiveUserDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Users> listInActiveUserDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	public Users getRegistrationDetailsById(String user_id) {
		// TODO Auto-generated method stub
		return activitiesDAO.getRegistrationDetailsById(user_id);
	}

	public void activateUser(String user_id) {
		// TODO Auto-generated method stub

	}

	public void inActivateUser(String user_id) {
		// TODO Auto-generated method stub

	}

}
