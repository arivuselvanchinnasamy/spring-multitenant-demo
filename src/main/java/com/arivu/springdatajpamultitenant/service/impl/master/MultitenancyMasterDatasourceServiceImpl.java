package com.arivu.springdatajpamultitenant.service.impl.master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arivu.springdatajpamultitenant.entity.master.MultitenantcyMasterDatasourceEntity;
import com.arivu.springdatajpamultitenant.exception.DatasourceNotFoundException;
import com.arivu.springdatajpamultitenant.repository.master.IMultitenancyMasterDatasourceRepository;
import com.arivu.springdatajpamultitenant.service.master.IMultitenancyMasterDatasourceService;

@Service
public class MultitenancyMasterDatasourceServiceImpl implements IMultitenancyMasterDatasourceService {

	@Autowired
	private IMultitenancyMasterDatasourceRepository multitenancyMasterDatasourceRepository;
	
	@Override
	public MultitenantcyMasterDatasourceEntity findByDataSourceKey(String dataSourceKey) {
		 MultitenantcyMasterDatasourceEntity  multitenantcyMasterDatasourceEntity = multitenancyMasterDatasourceRepository.findByDataSourceKey(dataSourceKey);
		 if(multitenantcyMasterDatasourceEntity == null) {
			 throw new DatasourceNotFoundException("Unable to find the datasource " + dataSourceKey);
		 }
		return multitenantcyMasterDatasourceEntity;
	}
	
	public List<MultitenantcyMasterDatasourceEntity> findAll() {
		List<MultitenantcyMasterDatasourceEntity> masterDatasourcesList =  multitenancyMasterDatasourceRepository.findAll();
		if(masterDatasourcesList == null ||  masterDatasourcesList.isEmpty()) {
			throw new DatasourceNotFoundException("There is no datasources");
		}
		return masterDatasourcesList;
	}

	@Override
	public void save(MultitenantcyMasterDatasourceEntity multitenantcyMasterDatasourceEntity) {
		multitenancyMasterDatasourceRepository.save(multitenantcyMasterDatasourceEntity);
	}

	@Override
	public void delete(long Id) {
		multitenancyMasterDatasourceRepository.delete(Id);
	}

}
