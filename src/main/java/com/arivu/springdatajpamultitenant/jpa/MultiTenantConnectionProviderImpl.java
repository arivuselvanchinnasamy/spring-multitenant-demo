package com.arivu.springdatajpamultitenant.jpa;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arivu.springdatajpamultitenant.constant.SpringDataJPAMultitenantConstants;

@Component(MultiTenantConnectionProviderImpl.BEAN_ID)
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7395318315512114572L;

	public final static String BEAN_ID = "multiTenantConnectionProvider";

	@Autowired
	private TenantDataSource tenantDataSource;

	private Logger log;

	public MultiTenantConnectionProviderImpl() {
		log = LoggerFactory.getLogger(getClass());		
	}

	@Override
	protected DataSource selectAnyDataSource() {
		log.debug("selectAnyDataSource , returning dafault tenantid");
		return tenantDataSource.getDataSource(SpringDataJPAMultitenantConstants.DEFAULT_TENANT_ID);
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		log.debug("selected Datasource {} ", tenantIdentifier);
		return  tenantDataSource.getDataSource(tenantIdentifier);
	}

	

}
