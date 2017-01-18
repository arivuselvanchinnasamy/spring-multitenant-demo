package com.arivu.springdatajpamultitenant.constant;

public class SpringDataJPAMultitenantConstants {

	public static final String CREATE_SCHEMA = "CREATE DATABASE IF NOT EXISTS";
	
	public static final String USE_SCHEMA = "USE";
	
	public static final String DROP_SCHEMA = "DROP DATABASE IF EXISTS";
	
	public static final String DEFAULT_TENANT_ID = "MULTITENANCY_MASTER";
	
	public static final String SLASH = "/";
	public static final String ROOT_PATH = SLASH;
	
	public static final String ID = "id";
	public static final String INDEX= "index" ;
	
	public static final String TENANTS = "tenants";
	public static final String TENANT = "tenant";
	
	public static final String ADD_TENANT = "addTenant";
	public static final String DELETE_TENANT = "deleteTenant";
	
	public static final String TENANT_ID = "tenantId";
	public static final String TENANT_NAME = "tenantName";
	
	public static final String SAVE = "save";
	public static final String CANCEL = "cancel";
	
	
	public static final String REDIRECT = "redirect:";
	public static final String REDIRECT_PATH = REDIRECT + SLASH;
	public static final String REDIRECT_TO_INDEX = REDIRECT_PATH + INDEX;
	public static final String ADD_TENANT_PATH= SLASH + ADD_TENANT;
	public static final String DELETE_TENANT_PATH= SLASH + DELETE_TENANT + SLASH + "{" + TENANT_NAME + "}";
	
	public static final String EMPLOYEES = "employees";
	public static final String EMPLOYEE = "employee";
		
	public static final String ADD_EMPLOYEE = "addEmployee";
	public static final String EDIT_EMPLOYEE = "editEmployee";
	public static final String DELETE_EMPLOYEE = "deleteEmployee";
	
	public static final String TENANT_PATH= ROOT_PATH + TENANT ;
	public static final String TENANTID_PATH = TENANT_PATH + SLASH + "{" + TENANT_ID + "}";
	
	public static final String REDIRECT_PATH_EMPLOYEES = REDIRECT+ TENANTID_PATH;
	
	public static final String ADD_EMPLOYEE_PATH= TENANTID_PATH + SLASH + ADD_EMPLOYEE;
	public static final String EDIT_EMPLOYEE_PATH= TENANTID_PATH + SLASH + EDIT_EMPLOYEE + SLASH +"{" + ID + "}";
	public static final String DELETE_EMPLOYEE_PATH= TENANTID_PATH + SLASH + DELETE_EMPLOYEE + SLASH +"{" + ID + "}";	
}
