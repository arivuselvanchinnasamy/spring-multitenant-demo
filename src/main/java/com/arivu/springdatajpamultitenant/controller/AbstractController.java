package com.arivu.springdatajpamultitenant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {
	
	
	protected Logger logger;

	public AbstractController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	
}
