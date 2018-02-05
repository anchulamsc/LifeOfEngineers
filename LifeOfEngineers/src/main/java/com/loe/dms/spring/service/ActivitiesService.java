package com.loe.dms.spring.service;

import java.util.List;

import com.loe.dms.spring.model.entity.Users;

public interface ActivitiesService {

	public void updateRegistrationDetails(Users users);

	public List<Users> listRegisteredUserDetails();

	public List<Users> listActiveUserDetails();

	public List<Users> listInActiveUserDetails();

	public Users getRegistrationDetailsById(String user_id);

	public void activateUser(String user_id);

	public void inActivateUser(String user_id);

}
