package com.arivu.springdatajpamultitenant.exception;

public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -196895950946473879L;

	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
}

