
package com.loe.dms.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.loe.dms.spring.controller.StaticPageController;
import com.loe.dms.spring.dao.StaticPageDAO;

@Service
public class StaticPageServiceImpl implements StaticPageService {

	private static final Logger logger = LoggerFactory.getLogger(StaticPageController.class);

	private StaticPageDAO staticPageDAO;

	@Autowired(required = true)
	@Qualifier(value = "staticPageDAO")
	public void setStaticPageDAO(StaticPageDAO staticPageDAO) {
		this.staticPageDAO = staticPageDAO;
	}

}
