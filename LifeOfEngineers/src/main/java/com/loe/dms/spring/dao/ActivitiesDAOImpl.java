package com.loe.dms.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.loe.dms.spring.model.entity.RegistrationDetails;
import com.loe.dms.spring.model.entity.UserRoles;
import com.loe.dms.spring.model.entity.Users;

@Repository
public class ActivitiesDAOImpl implements ActivitiesDAO {
	private static final Logger logger = LoggerFactory.getLogger(ActivitiesDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public Session openSession() {
		return this.sessionFactory.openSession();
	}

	public void closeSession(Session session) {
		if (session != null) {
			session.close();
		}
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
		Session session = null;
		Users retrievedUser = null;
		try {
			session = openSession();
			String userQueryString = "from Users where user_id = :user_id";
			Query userQuery = session.createQuery(userQueryString);
			userQuery.setString("user_id", user_id);
			Object userQueryResult = userQuery.uniqueResult();
			retrievedUser = (Users) userQueryResult;

			if (retrievedUser != null) {
				String roleQueryString = "from UserRoles where user_id = :user_id";
				Query roleQuery = session.createQuery(roleQueryString);
				roleQuery.setString("user_id", user_id);
				Object roleQueryResult = roleQuery.uniqueResult();
				UserRoles retrievedUserRole = (UserRoles) roleQueryResult;

				String registrationQueryString = "from RegistrationDetails where user_id = :user_id";
				Query registrationQuery = session.createQuery(registrationQueryString);
				registrationQuery.setString("user_id", user_id);
				Object registrationQueryResult = registrationQuery.uniqueResult();
				RegistrationDetails registrationDetails = (RegistrationDetails) registrationQueryResult;

				retrievedUser.setUserRoles(retrievedUserRole);
				retrievedUser.setRegistrationDetails(registrationDetails);
			}
		} finally {
			closeSession(session);
		}

		return retrievedUser;

	}

	public void activateUser(String user_id) {
		// TODO Auto-generated method stub

	}

	public void inActivateUser(String user_id) {
		// TODO Auto-generated method stub

	}

}
