package com.loe.dms.spring.dao;

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
public class RegistrationDAOImpl implements RegistrationDAO {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationDAOImpl.class);

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

	public void enrollRegistrationDetails(Users users) {
		UserRoles userRoles = users.getUserRoles();
		RegistrationDetails registrationDetails = users.getRegistrationDetails();
		Session session = null;
		try {
			session = openSession();
			session.persist(users);
			session.persist(userRoles);
			session.persist(registrationDetails);
			session.flush();
		} finally {
			closeSession(session);
		}
	}

	public Users performUserLogin(Users users) {
		Session session = null;
		Users retrievedUser = null;
		try {
			session = openSession();
			String userQueryString = "from Users where user_name = :user_name";
			Query userQuery = session.createQuery(userQueryString);
			userQuery.setString("user_name", users.getUser_name());
			Object userQueryResult = userQuery.uniqueResult();
			retrievedUser = (Users) userQueryResult;
			if (retrievedUser != null) {
				String roleQueryString = "from UserRoles where user_name = :user_name";
				Query roleQuery = session.createQuery(roleQueryString);
				roleQuery.setString("user_name", users.getUser_name());
				Object roleQueryResult = roleQuery.uniqueResult();
				UserRoles retrievedUserRole = (UserRoles) roleQueryResult;

				retrievedUser.setUserRoles(retrievedUserRole);
			}
		} finally {
			closeSession(session);
		}
		logger.info("Users loaded successfully, retrievedUser = " + retrievedUser);
		return retrievedUser;
	}

	public UserRoles emailVerification(String user_id, String verificationCode) {
		Session session = null;
		UserRoles retrievedUserRole = null;
		try {
			session = openSession();
			String roleQueryString = "from UserRoles where user_id = :user_id";
			Query roleQuery = session.createQuery(roleQueryString);
			roleQuery.setString("user_id", user_id);
			Object roleQueryResult = roleQuery.uniqueResult();
			retrievedUserRole = (UserRoles) roleQueryResult;
			if (retrievedUserRole != null) {
				retrievedUserRole.setEmail_verified(verificationCode);
				session.merge(retrievedUserRole);
				session.flush();
			}
		} finally {
			closeSession(session);
		}
		return retrievedUserRole;
	}

}
