package com.arivu.springdatajpamultitenant.exception;

public class DatasourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -196895950946473879L;

	public DatasourceNotFoundException(String message) {
		super(message);
	}
	
}

