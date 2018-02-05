package com.loe.dms.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.loe.dms.spring.data.util.DataTransformationUtil;
import com.loe.dms.spring.model.data.EventsInfo;
import com.loe.dms.spring.model.data.ServiceResponse;
import com.loe.dms.spring.model.data.UserSession;
import com.loe.dms.spring.model.entity.EventDetails;
import com.loe.dms.spring.service.EventsService;
import com.loe.dms.spring.util.WebUtil;
import com.loe.dms.spring.validator.EventsInfoValidator;

@Controller
@RequestMapping("/events")
public class EventsController {
	private static final Logger logger = LoggerFactory.getLogger(EventsController.class);

	@Autowired
	private EventsInfoValidator validator;

	private EventsService eventsService;

	@Autowired(required = true)
	@Qualifier(value = "eventsService")
	public void setEventsService(EventsService eventsService) {
		this.eventsService = eventsService;
	}

	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String loadEventsPage(HttpServletRequest request, Model model) {
		logger.info(">>>>> loadEventsPage >>>>");
		EventsInfo eventsInfo = new EventsInfo();
		eventsInfo.setAddView(true);
		eventsInfo.setDisplayView(false);
		UserSession userSession = WebUtil.getUserSession(request);
		if (userSession.getUser_id() != null && userSession.getUser_id() != "") {
			eventsInfo.setUser_id(userSession.getUser_id());
			List<EventDetails> eventsList = eventsService.listEventDetails(userSession.getUser_id());
			if(eventsList !=null && !eventsList.isEmpty()){
				eventsInfo.setAddView(false);
				eventsInfo.setDisplayView(true);
				model.addAttribute("eventsList", eventsList);
			}
		}
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		model.addAttribute("eventsInfo", eventsInfo);
		return "events";
	}
	
	@RequestMapping(value = "/addeventview", method = RequestMethod.GET)
	public String loadAddEventsView(HttpServletRequest request, Model model) {
		logger.info(">>>>> loadAddEventsView >>>>");
		EventsInfo eventsInfo = new EventsInfo();
		eventsInfo.setAddView(true);
		eventsInfo.setDisplayView(false);
		UserSession userSession = WebUtil.getUserSession(request);
		if (userSession.getUser_id() != null && userSession.getUser_id() != "") {
			eventsInfo.setUser_id(userSession.getUser_id());
		}
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		model.addAttribute("eventsInfo", eventsInfo);
		return "events";
	}

	@RequestMapping(value = "/addEvents", method = RequestMethod.POST)
	public String addEvents(HttpServletRequest request, Model model, @ModelAttribute("eventsInfo") EventsInfo eventsInfo, BindingResult result) {
		logger.info(">>>>> addEvents >>>>");
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		// Validation code
		validator.validate(eventsInfo, result);

		// Check validation errors
		if (result.hasErrors()) {
			eventsInfo.setAddView(true);
			return "events";
		}

		EventDetails eventDetails = DataTransformationUtil.prepareEventsBusinessData(eventsInfo);

		ServiceResponse serviceResponse = eventsService.addEvent(eventDetails);
		if (serviceResponse != null && serviceResponse.hasErrors()) {
		}

		return "redirect:/events/event";
	}

	@RequestMapping(value = "/updateEvents", method = RequestMethod.POST)
	public String updateEvents(HttpServletRequest request, Model model) {
		logger.info(">>>>> updateEvents >>>>");
		EventsInfo eventsInfo = new EventsInfo();
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		model.addAttribute("eventsInfo", eventsInfo);
		return "events";
	}

	@RequestMapping(value = "/deleteEvents", method = RequestMethod.POST)
	public String deleteEvents(HttpServletRequest request, Model model) {
		logger.info(">>>>> updateEvents >>>>");
		EventsInfo eventsInfo = new EventsInfo();
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		model.addAttribute("eventsInfo", eventsInfo);
		return "events";
	}

}
