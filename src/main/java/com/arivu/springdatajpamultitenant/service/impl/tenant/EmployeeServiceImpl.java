package com.arivu.springdatajpamultitenant.service.impl.tenant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arivu.springdatajpamultitenant.entity.tenant.EmployeeEntity;
import com.arivu.springdatajpamultitenant.exception.EmployeeNotFoundException;
import com.arivu.springdatajpamultitenant.repository.tenant.IEmployeeRepository;
import com.arivu.springdatajpamultitenant.service.tenant.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
 
	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Override
	public List<EmployeeEntity> findAll() {
		List<EmployeeEntity> employeesList = employeeRepository.findAll();
		if(employeesList == null || employeesList.isEmpty()) {
			throw new EmployeeNotFoundException("There is no employees");
		}
		return employeeRepository.findAll();
	}

	@Override
	public void save(EmployeeEntity employee) {
		employeeRepository.save(employee);		
	}

	@Override
	public EmployeeEntity findById(long id) {
		EmployeeEntity employeeFromDB =  employeeRepository.findOne(id);
		if(employeeFromDB == null) {
			throw new EmployeeNotFoundException("Employee { " + id + "} doest not exits");
		}
		return employeeFromDB;
	}

	@Override
	public void delete(long Id) {
		employeeRepository.delete(Id);		
	}

}
