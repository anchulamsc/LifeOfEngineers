package com.loe.dms.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.loe.dms.spring.model.entity.EventDetails;

@Repository
public class EventsDAOImpl implements EventsDAO {

	private static final Logger logger = LoggerFactory.getLogger(EventsDAOImpl.class);

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

	public void addEvent(EventDetails eventDetails) {
		Session session = null;
		try {
			session = openSession();
			session.save(eventDetails);
			session.flush();
		} finally {
			closeSession(session);
		}
	}

	public void updateEvent(EventDetails eventDetails) {
		Session session = null;
		try {
			session = openSession();
			session.merge(eventDetails);
			session.flush();
		} finally {
			closeSession(session);
		}

	}

	public void deleteEventById(String user_id, String event_id) {
		// TODO Auto-generated method stub

	}

	public List<EventDetails> getEventDetailsById(String user_id) {
		Session session = null;
		List<EventDetails> listofEventDetails = null;
		try {
			session = openSession();
			listofEventDetails = session.createQuery("from EventDetails").list();
			for (EventDetails eventDetails : listofEventDetails) {
				logger.info("EventDetails List::" + eventDetails);
			}

		} finally {
			closeSession(session);
		}
		return listofEventDetails;

	}

}
