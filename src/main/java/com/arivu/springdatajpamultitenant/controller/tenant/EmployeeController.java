package com.arivu.springdatajpamultitenant.controller.tenant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.arivu.springdatajpamultitenant.constant.SpringDataJPAMultitenantConstants;
import com.arivu.springdatajpamultitenant.controller.AbstractController;
import com.arivu.springdatajpamultitenant.entity.tenant.EmployeeEntity;
import com.arivu.springdatajpamultitenant.service.tenant.IEmployeeService;

@Controller
public class EmployeeController extends AbstractController {

	@Autowired
	private IEmployeeService employeeService;


	@RequestMapping(value=SpringDataJPAMultitenantConstants.TENANTID_PATH, method=RequestMethod.GET)
	public String employees(@PathVariable String tenantId, Model model) {
		model.addAttribute(SpringDataJPAMultitenantConstants.TENANT_ID, tenantId);
		model.addAttribute(SpringDataJPAMultitenantConstants.EMPLOYEE, new EmployeeEntity());
		model.addAttribute(SpringDataJPAMultitenantConstants.EMPLOYEES, employeeService.findAll());
		return SpringDataJPAMultitenantConstants.EMPLOYEES;
	}

	@RequestMapping(value = SpringDataJPAMultitenantConstants.ADD_EMPLOYEE_PATH, method = RequestMethod.GET)
	public String addEmployee(@PathVariable String tenantId, Model model) {
		logger.debug("Adding new employee for tenant {}", tenantId);
		model.addAttribute(SpringDataJPAMultitenantConstants.TENANT_ID, tenantId);
		model.addAttribute(SpringDataJPAMultitenantConstants.EMPLOYEE, new EmployeeEntity());
		return SpringDataJPAMultitenantConstants.ADD_EMPLOYEE;
	}
	@RequestMapping(value = SpringDataJPAMultitenantConstants.EDIT_EMPLOYEE_PATH, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String tenantId, @PathVariable long id, Model model ) {
		logger.debug("Editing employee information {}", id);
		model.addAttribute(SpringDataJPAMultitenantConstants.TENANT_ID, tenantId);
		model.addAttribute(SpringDataJPAMultitenantConstants.EMPLOYEE, employeeService.findById(id));
		return SpringDataJPAMultitenantConstants.EDIT_EMPLOYEE;
	}
	@RequestMapping(value = SpringDataJPAMultitenantConstants.EDIT_EMPLOYEE_PATH, method = RequestMethod.POST)
	public String editEmployee(@PathVariable String tenantId, @ModelAttribute EmployeeEntity employee, Model model, 
			@RequestParam(value="action", required=true) String action) {
		logger.debug("Editing employee information {}", employee.getId());
		model.addAttribute(SpringDataJPAMultitenantConstants.TENANT_ID, tenantId);
		if(SpringDataJPAMultitenantConstants.SAVE.equals(action)) {
			employeeService.save(employee);			
		} else {
			logger.debug("Editing employee information cancelled");
		}
		model.addAttribute(SpringDataJPAMultitenantConstants.EMPLOYEES, employeeService.findAll());
		return SpringDataJPAMultitenantConstants.REDIRECT_PATH_EMPLOYEES;
	}
	@RequestMapping(value = SpringDataJPAMultitenantConstants.ADD_EMPLOYEE_PATH, method = RequestMethod.POST)
	@Transactional
	public String addEmployee(@PathVariable String tenantId,@ModelAttribute EmployeeEntity employee, Model model,  @RequestParam(value="action", required=true) String action) {
		model.addAttribute(SpringDataJPAMultitenantConstants.TENANT_ID, tenantId);
		if(SpringDataJPAMultitenantConstants.SAVE.equals(action) && employee != null) {			
			employeeService.save(employee);
		}	
		else {
			logger.debug("Adding employee information cancelled");
		}
		model.addAttribute(SpringDataJPAMultitenantConstants.EMPLOYEES, employeeService.findAll());
		return SpringDataJPAMultitenantConstants.REDIRECT_PATH_EMPLOYEES;
	}
	
	@RequestMapping(value = SpringDataJPAMultitenantConstants.DELETE_EMPLOYEE_PATH)
	@Transactional
	public String deleteEmployee(@PathVariable String tenantId,@PathVariable long id, Model model) {
		model.addAttribute(SpringDataJPAMultitenantConstants.TENANT_ID, tenantId);
		employeeService.delete(id);
		model.addAttribute(SpringDataJPAMultitenantConstants.EMPLOYEES, employeeService.findAll());
		return SpringDataJPAMultitenantConstants.REDIRECT_PATH_EMPLOYEES;
	}
}
