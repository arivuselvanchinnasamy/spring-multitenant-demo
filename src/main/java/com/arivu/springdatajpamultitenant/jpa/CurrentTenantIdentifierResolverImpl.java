package com.arivu.springdatajpamultitenant.jpa;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.arivu.springdatajpamultitenant.constant.SpringDataJPAMultitenantConstants;
import com.arivu.springdatajpamultitenant.context.TenantContext;

@Component(CurrentTenantIdentifierResolverImpl.BEAN_ID)
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {
	
	public final static String BEAN_ID = "currentTenantIdentifier";
	
	private final Logger logger;
	
	public CurrentTenantIdentifierResolverImpl() {
		logger = LoggerFactory.getLogger(this.getClass());
	}
	@Override
	public String resolveCurrentTenantIdentifier() {
		String currentDataSourceKey  =  TenantContext.getCurrentTenant();

		if(currentDataSourceKey==null) {
			currentDataSourceKey = SpringDataJPAMultitenantConstants.DEFAULT_TENANT_ID;
		}
		logger.debug("currentDataSourceKey {}", currentDataSourceKey);
		return currentDataSourceKey;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}