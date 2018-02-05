package com.loe.dms.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.loe.dms.spring.data.util.DataTransformationUtil;
import com.loe.dms.spring.model.data.ErrorInfoData;
import com.loe.dms.spring.model.data.RegistrationInfo;
import com.loe.dms.spring.model.data.ServiceResponse;
import com.loe.dms.spring.model.data.UserSession;
import com.loe.dms.spring.model.entity.UserRoles;
import com.loe.dms.spring.model.entity.Users;
import com.loe.dms.spring.service.RegistrationService;
import com.loe.dms.spring.util.EmailSenderUtil;
import com.loe.dms.spring.util.SecurityUtil;
import com.loe.dms.spring.util.WebUtil;
import com.loe.dms.spring.validator.RegistrationValidator;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	private RegistrationService registrationService;

	@Autowired
	private RegistrationValidator validator;

	@Autowired(required = true)
	@Qualifier(value = "registrationService")
	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String loadRegistrationPage(HttpServletRequest request, Model model) {
		logger.info(">>>>> loadRegistrationPage >>>>");
		RegistrationInfo registrationInfo = new RegistrationInfo();
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		model.addAttribute("registrationInfo", registrationInfo);
		return "registration";
	}

	@RequestMapping(value = "/enroll", method = RequestMethod.POST)
	public String continueRegistration(HttpServletRequest request, Model model, @ModelAttribute("registrationInfo") RegistrationInfo registrationInfo, BindingResult result) {
		logger.info(">>>>> continueRegistration >>>>");
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		// Validation code
		validator.validate(registrationInfo, result);

		// Check validation errors
		if (result.hasErrors()) {
			return "registration";
		}
		Users users = DataTransformationUtil.prepareRegistrationUsersBusinessData(registrationInfo);

		ServiceResponse serviceResponse = registrationService.enrollRegistrationDetails(users);
		if (serviceResponse != null && serviceResponse.hasErrors()) {
			registrationInfo.setErrorInfoData(serviceResponse.getErrorInfoData());
			validator.validateServiceErrors(registrationInfo, result);
			if (result.hasErrors()) {
				return "registration";
			}
		}

		boolean emailSuccess = EmailSenderUtil.sendEmailVerificationMessage(users.getUser_name(), users.getUser_id(), request.getRequestURL().toString());
		ErrorInfoData errorInfoData = new ErrorInfoData();
		if (emailSuccess) {
			errorInfoData.addErrorInfo("An email sent to your mail id,please click on verify to complete the registration process.");
		} else {
			errorInfoData.addErrorInfo("We are facing system difficulties while sending verification link,please contact admin or will send later.");
			registrationService.emailVerification(registrationInfo.getUser_name(), "S");
		}
		registrationInfo.setErrorInfoData(errorInfoData);
		validator.validateServiceErrors(registrationInfo, result);

		model.addAttribute("userSession", WebUtil.getUserSession(request));
		model.addAttribute("registrationInfo", registrationInfo);

		return "registration";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loadLoginPage(HttpServletRequest request, Model model) {
		logger.info(">>>>> loadLoginPage >>>>");
		RegistrationInfo registrationInfo = new RegistrationInfo();
		registrationInfo.setLoginview(true);
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		model.addAttribute("registrationInfo", registrationInfo);
		return "login";
	}

	@RequestMapping(value = "/performlogin", method = RequestMethod.POST)
	public String performLogin(HttpServletRequest request, Model model, @ModelAttribute("registrationInfo") RegistrationInfo registrationInfo, BindingResult result) {
		logger.info(">>>>> performLogin >>>>");
		String navigateTo = "login";
		registrationInfo.setLoginview(true);
		registrationInfo.setForgotview(false);
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		model.addAttribute("registrationInfo", registrationInfo);
		// Validation code
		validator.validateLogin(registrationInfo, result);

		// Check validation errors
		if (result.hasErrors()) {
			return navigateTo;
		}
		Users users = DataTransformationUtil.prepareLoginUsersBusinessData(registrationInfo);
		Users retrievedUser = registrationService.performUserLogin(users);
		UserSession userSession = WebUtil.getUserSession(request);
		if (retrievedUser == null) {
			ErrorInfoData errorInfoData = new ErrorInfoData();
			errorInfoData.addErrorInfo("Entered email is not registered in system,please try to register or login with valid email id.");
			registrationInfo.setErrorInfoData(errorInfoData);
			validator.validateServiceErrors(registrationInfo, result);
			if (result.hasErrors()) {
				return navigateTo;
			}
		} else {
			if (registrationInfo.getUser_name().equalsIgnoreCase(retrievedUser.getUser_name()) && registrationInfo.getPassword().equalsIgnoreCase(SecurityUtil.decrypt(retrievedUser.getPassword()))) {
				UserRoles userRoles = retrievedUser.getUserRoles();
				if (userRoles != null) {
					userSession.setUser_id(retrievedUser.getUser_id());
					userSession.setUser_name(retrievedUser.getUser_name());
					userSession.setRole(userRoles.getRole());
					userSession.setActiveUser(userRoles.getActive_flag());
					if ("Y".equalsIgnoreCase(userRoles.getEmail_verified()) && "Y".equalsIgnoreCase(userRoles.getActive_flag())) {
						WebUtil.setSessionTimout(request);
						navigateTo = "redirect:/activities/activity";
					} else if ("N".equalsIgnoreCase(userRoles.getEmail_verified())) {
						ErrorInfoData errorInfoData = new ErrorInfoData();
						errorInfoData.addErrorInfo("The registered user is not verified yet,please perform verification from the email you have received.");
						registrationInfo.setErrorInfoData(errorInfoData);
						validator.validateServiceErrors(registrationInfo, result);
						if (result.hasErrors()) {
							return navigateTo;
						}
					} else if ("N".equalsIgnoreCase(userRoles.getActive_flag())) {
						ErrorInfoData errorInfoData = new ErrorInfoData();
						errorInfoData.addErrorInfo("The registered user is not activated yet,please contact operations team.");
						registrationInfo.setErrorInfoData(errorInfoData);
						validator.validateServiceErrors(registrationInfo, result);
						if (result.hasErrors()) {
							return navigateTo;
						}
					}
				}
			} else {
				ErrorInfoData errorInfoData = new ErrorInfoData();
				errorInfoData.addErrorInfo("Entered password does not match with the registered password,please try with valid password.");
				registrationInfo.setErrorInfoData(errorInfoData);
				validator.validateServiceErrors(registrationInfo, result);
				if (result.hasErrors()) {
					return navigateTo;
				}
			}
		}
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		return navigateTo;
	}

	@RequestMapping(value = "/performLogOff", method = RequestMethod.GET)
	public String performLogOff(HttpServletRequest request) {
		logger.info(">>>>> performLogOff >>>>");
		WebUtil.invalidateSession(request);
		return "home";
	}

	@RequestMapping(value = "/loadforgot", method = RequestMethod.GET)
	public String loadForgotView(HttpServletRequest request, Model model) {
		logger.info(">>>>> loadForgotView >>>>");
		RegistrationInfo registrationInfo = new RegistrationInfo();
		registrationInfo.setForgotview(true);
		;
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		model.addAttribute("registrationInfo", registrationInfo);
		return "login";
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String sendPassword(HttpServletRequest request, Model model, @ModelAttribute("registrationInfo") RegistrationInfo registrationInfo, BindingResult result) {
		logger.info(">>>>> sendPassword >>>>");

		String navigateTo = "login";
		registrationInfo.setLoginview(false);
		registrationInfo.setForgotview(true);
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		model.addAttribute("registrationInfo", registrationInfo);
		// Validation code
		validator.validateEmailId(registrationInfo, result);

		// Check validation errors
		if (result.hasErrors()) {
			return navigateTo;
		}

		Users users = DataTransformationUtil.prepareLoginUsersBusinessData(registrationInfo);
		Users retrievedUser = registrationService.performUserLogin(users);
		if (retrievedUser == null) {
			ErrorInfoData errorInfoData = new ErrorInfoData();
			errorInfoData.addErrorInfo("Entered email is not registered in system,please try to register or login with valid email id.");
			registrationInfo.setErrorInfoData(errorInfoData);
			validator.validateServiceErrors(registrationInfo, result);
			if (result.hasErrors()) {
				return navigateTo;
			}
		} else {
			boolean emailSuccess = EmailSenderUtil.sendPasswordMessage(retrievedUser.getUser_name(), SecurityUtil.decrypt(retrievedUser.getPassword()));
			ErrorInfoData errorInfoData = new ErrorInfoData();
			if (emailSuccess) {
				errorInfoData.addErrorInfo("System registered password mailed to your email,please try login with that or contact admin.");
			} else {
				errorInfoData.addErrorInfo("We are facing system difficulties while sending password,please contact admin.");
			}
			registrationInfo.setErrorInfoData(errorInfoData);
			validator.validateServiceErrors(registrationInfo, result);
			if (result.hasErrors()) {
				registrationInfo.setLoginview(true);
				registrationInfo.setForgotview(false);
				model.addAttribute("userSession", WebUtil.getUserSession(request));
				model.addAttribute("registrationInfo", registrationInfo);
				return "login";
			}
		}

		return navigateTo;
	}

	@RequestMapping(value = "/verifyemail/{user_id}", method = RequestMethod.GET)
	public String verifyEmail(HttpServletRequest request, @PathVariable String user_id, Model model) {
		logger.info(">>>>> verifyEmail >>>>");
		RegistrationInfo registrationInfo = new RegistrationInfo();
		registrationInfo.setLoginview(true);
		if (user_id != null && !user_id.isEmpty()) {
			ServiceResponse serviceResponse = registrationService.emailVerification(user_id, "Y");
			if (serviceResponse.hasErrors()) {
				registrationInfo.setInfoMessage(serviceResponse.getErrorInfoData().getErrorInfos().get(0));
			}

		}
		model.addAttribute("userSession", WebUtil.getUserSession(request));
		model.addAttribute("registrationInfo", registrationInfo);
		return "login";
	}

}
