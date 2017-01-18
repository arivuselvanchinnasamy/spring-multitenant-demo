package com.arivu.springdatajpamultitenant.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.arivu.springdatajpamultitenant.jpa.CurrentTenantIdentifierResolverImpl;
import com.arivu.springdatajpamultitenant.jpa.MultiTenantConnectionProviderImpl;

@Configuration
@EnableConfigurationProperties(JpaProperties.class)
public class MultiTenancyJpaConfiguration {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JpaProperties jpaProperties;
	
	@Autowired
	@Qualifier(MultiTenantConnectionProviderImpl.BEAN_ID)
	private MultiTenantConnectionProvider  multiTenantConnectionProvider ;

	@Autowired
	@Qualifier(CurrentTenantIdentifierResolverImpl.BEAN_ID)
	private CurrentTenantIdentifierResolver currentTenantIdentifierResolver;

	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
         Map<String, Object> hibernateProps = new LinkedHashMap<>();
         hibernateProps.putAll(jpaProperties.getHibernateProperties(dataSource));
       
         hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
         hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
         hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
         hibernateProps.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
         
       
         return builder.dataSource(dataSource).packages("com.arivu.springdatajpamultitenant").properties(hibernateProps).jta(false).build();
    }
}
