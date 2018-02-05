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

import com.loe.dms.spring.service.StaticPageService;
import com.loe.dms.spring.util.WebUtil;

@Controller
public class StaticPageController {
	private static final Logger logger = LoggerFactory.getLogger(StaticPageController.class);

	private StaticPageService staticPageService;

	@Autowired(required = true)
	@Qualifier(value = "staticPageService")
	public void setStaticPageService(StaticPageService staticPageService) {
		this.staticPageService = staticPageService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loadHomePage(HttpServletRequest request, Model model) {
		logger.info(">>>>> loadHomePage >>>>");
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		return "home";
	}

	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public String loadAboutUsPage(HttpServletRequest request, Model model) {
		logger.info(">>>>> loadAboutUsPage >>>>");
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		return "aboutus";
	}

	

}
