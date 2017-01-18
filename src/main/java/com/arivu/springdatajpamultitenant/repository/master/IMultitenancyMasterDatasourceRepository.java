package com.arivu.springdatajpamultitenant.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arivu.springdatajpamultitenant.entity.master.MultitenantcyMasterDatasourceEntity;


public interface IMultitenancyMasterDatasourceRepository extends JpaRepository<MultitenantcyMasterDatasourceEntity, Long>{

	MultitenantcyMasterDatasourceEntity findByDataSourceKey(String dataSourceKey);
}
