package com.arivu.springdatajpamultitenant.jpa;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import com.arivu.springdatajpamultitenant.constant.SpringDataJPAMultitenantConstants;
import com.arivu.springdatajpamultitenant.entity.master.MultitenantcyMasterDatasourceEntity;

@Component
public class TenantDataSource {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public DataSourceProperties dataSourceProperties;

	@Autowired
	private JdbcTemplate jdbcTemplateObject;

	private Map<Object, Object> tenantDataSourcesMap;

	private Logger log;

	@Autowired
	public TenantDataSource() {
		tenantDataSourcesMap = new LinkedHashMap<Object, Object>();
		log = LoggerFactory.getLogger(getClass());
	}

	@PostConstruct
	void addDefaultDatasourceToMap() {
		tenantDataSourcesMap.put(SpringDataJPAMultitenantConstants.DEFAULT_TENANT_ID, dataSource);
	}

	public DataSource getDataSource(String dataSourceName) {
		DataSource dataSource = null;
		log.debug("getDataSource().dataSourceName {}", dataSourceName);
		if (tenantDataSourcesMap.containsKey(dataSourceName)) {
			dataSource = (DataSource) tenantDataSourcesMap.get(dataSourceName);
		}
		return dataSource;
	}

	public void loadTenant(MultitenantcyMasterDatasourceEntity tenantDatasource) {
		if (!tenantDataSourcesMap.containsKey(tenantDatasource.getDataSourceKey())) {
			tenantDataSourcesMap.put(tenantDatasource.getDataSourceKey(), createDataSource(tenantDatasource));
		}
	}

	public void loadAllTenants(List<MultitenantcyMasterDatasourceEntity> tenantDatasourcesList) {
		tenantDatasourcesList.stream().forEach(tenatDataSource -> {
			if (!tenantDataSourcesMap.containsKey(tenatDataSource.getDataSourceKey())) {
				tenantDataSourcesMap.put(tenatDataSource.getDataSourceKey(), createDataSource(tenatDataSource));
			}
		});

	}

	private DataSource createDataSource(MultitenantcyMasterDatasourceEntity tenatDataSource) {
		DataSource dataSource = null;

		if (tenatDataSource != null) {
			dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource();
			DataSourceBuilder factory = DataSourceBuilder.create()
					.driverClassName(dataSourceProperties.getDriverClassName())
					.username(tenatDataSource.getDataSourceUserName()).password(tenatDataSource.getDataSourcePassword())
					.url("jdbc:mysql://localhost:3306/" + tenatDataSource.getDataSourceKey().trim());
			dataSource = factory.build();
		}
		return dataSource;
	}

	public void createSchema(String dataSourceName) throws SQLException {
		if (tenantDataSourcesMap.containsKey(dataSourceName)) {
			jdbcTemplateObject.execute(SpringDataJPAMultitenantConstants.CREATE_SCHEMA + " " + dataSourceName);
			jdbcTemplateObject.execute(SpringDataJPAMultitenantConstants.USE_SCHEMA + " " + dataSourceName);
			DataSource currentDataSource = (DataSource) tenantDataSourcesMap.get(dataSourceName);
			ClassPathResource resource = new ClassPathResource("dbscripts/create_employee.sql");
		    ScriptUtils.executeSqlScript(currentDataSource.getConnection(), new EncodedResource(resource, "UTF-8"));
		    jdbcTemplateObject.execute(SpringDataJPAMultitenantConstants.USE_SCHEMA + " " + SpringDataJPAMultitenantConstants.DEFAULT_TENANT_ID);
		} 
	}
	
	public void dropSchema(String dataSourceName) {
		if (tenantDataSourcesMap.containsKey(dataSourceName)) {
			jdbcTemplateObject.execute(SpringDataJPAMultitenantConstants.DROP_SCHEMA + " " + dataSourceName);
		}
	}
}
