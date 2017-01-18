package com.arivu.springdatajpamultitenant.repository.tenant;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arivu.springdatajpamultitenant.entity.tenant.EmployeeEntity;


public interface IEmployeeRepository  extends JpaRepository<EmployeeEntity, Long>{

}
