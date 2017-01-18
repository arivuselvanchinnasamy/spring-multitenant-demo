package com.arivu.springdatajpamultitenant.service.tenant;

import java.util.List;

import com.arivu.springdatajpamultitenant.entity.tenant.EmployeeEntity;

public interface IEmployeeService {
	
	public List<EmployeeEntity> findAll();
	
	public EmployeeEntity findById(long Id);
	
	public void save(EmployeeEntity employee);
	
	public void delete(long Id);
}
