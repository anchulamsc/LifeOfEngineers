package com.loe.dms.spring.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StaticPageDAOImpl implements StaticPageDAO{

	private static final Logger logger = LoggerFactory.getLogger(StaticPageDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

}
