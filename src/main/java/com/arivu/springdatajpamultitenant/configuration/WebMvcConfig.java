package com.arivu.springdatajpamultitenant.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.arivu.springdatajpamultitenant.interceptor.MultiTenancyInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	 
    @Autowired
    private MultiTenancyInterceptor multiTenancyInterceptor;
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(multiTenancyInterceptor);
    }
}
