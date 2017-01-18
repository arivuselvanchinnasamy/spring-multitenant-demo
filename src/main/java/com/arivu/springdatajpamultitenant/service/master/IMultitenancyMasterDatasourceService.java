package com.arivu.springdatajpamultitenant.service.master;

import java.util.List;

import com.arivu.springdatajpamultitenant.entity.master.MultitenantcyMasterDatasourceEntity;

public interface IMultitenancyMasterDatasourceService {
	
	MultitenantcyMasterDatasourceEntity findByDataSourceKey(String dataSourceKey);
	
	List<MultitenantcyMasterDatasourceEntity> findAll();
	
	public void save(MultitenantcyMasterDatasourceEntity multitenantcyMasterDatasourceEntity);
	
	public void delete(long Id);
}
