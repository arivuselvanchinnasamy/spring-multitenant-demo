package com.arivu.springdatajpamultitenant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SpringMultitenantExceptionHandler {
	@SuppressWarnings("unused")
	private final Logger logger;

	public SpringMultitenantExceptionHandler() {
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	@ExceptionHandler({EmployeeNotFoundException.class})
	public ModelAndView getEmployeesUnavailable(EmployeeNotFoundException ex) {
	    return new ModelAndView("employees", "error", ex.getMessage());
	}

}
