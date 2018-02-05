package com.loe.dms.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.loe.dms.spring.model.data.UserSession;
import com.loe.dms.spring.model.entity.Users;
import com.loe.dms.spring.service.ActivitiesService;
import com.loe.dms.spring.util.WebUtil;

@Controller
@RequestMapping("/activities")
public class ActivitiesController {
	private static final Logger logger = LoggerFactory.getLogger(ActivitiesController.class);

	private ActivitiesService activitiesService;

	@Autowired(required = true)
	@Qualifier(value = "activitiesService")
	public void setActivitiesService(ActivitiesService activitiesService) {
		this.activitiesService = activitiesService;
	}

	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String loadActivitiesPage(HttpServletRequest request, Model model) {
		logger.info(">>>>> loadActivitiesPage >>>>");
		UserSession userSession = WebUtil.getUserSession(request);
		if ("Y".equalsIgnoreCase(userSession.getActiveUser()) && userSession.getUser_id() != null && userSession.getUser_id() != "") {
			Users users = activitiesService.getRegistrationDetailsById(userSession.getUser_id());
			model.addAttribute("users", users);
		}
		model.addAttribute("userSession", userSession);
		return "activities";
	}

}
