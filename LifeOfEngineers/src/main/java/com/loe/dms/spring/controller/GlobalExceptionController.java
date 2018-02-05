package com.loe.dms.spring.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.loe.dms.spring.model.data.ExceptionData;
import com.loe.dms.spring.util.WebUtil;

@ControllerAdvice
public class GlobalExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(HttpServletRequest request, Exception ex) {

		logger.error("Requested URL=" + request.getRequestURL());
		logger.error("Exception Raised=" + ex);
		logger.error("Message=" + ex.getMessage());

		WebUtil.invalidateSession(request);

		ExceptionData exceptionData = new ExceptionData();

		exceptionData.setErrorMessage(ex.getMessage());
		exceptionData.setExceptionClass("Exception Raised=" + ex);
		exceptionData.setStackTrace(getStackTrace(ex));

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exceptionData", exceptionData);

		modelAndView.setViewName("error");
		return modelAndView;

	}

	public static List<String> getExceptionMessageChain(Throwable throwable) {
		List<String> result = new ArrayList<String>();
		while (throwable != null) {
			result.add(throwable.getMessage());
			throwable = throwable.getCause();
		}
		return result;
	}

	public static String getStackTrace(final Throwable throwable) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}

}
