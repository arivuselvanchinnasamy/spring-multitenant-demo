package com.arivu.springdatajpamultitenant.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.arivu.springdatajpamultitenant.constant.SpringDataJPAMultitenantConstants;
import com.arivu.springdatajpamultitenant.context.TenantContext;

@Component
public class MultiTenancyInterceptor extends HandlerInterceptorAdapter implements WebRequestInterceptor {

	private Logger log;

	public MultiTenancyInterceptor() {
		log = LoggerFactory.getLogger(this.getClass());
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse res, Object handler) throws Exception {
		String tenantName = null;
		
		@SuppressWarnings("unchecked")
		Map<String, Object> pathVars = (Map<String, Object>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		if (pathVars.containsKey(SpringDataJPAMultitenantConstants.TENANT_ID)) {
			tenantName = (String)pathVars.get(SpringDataJPAMultitenantConstants.TENANT_ID);
			log.debug("Current Tenant {} from pathVars", tenantName);
		}
		if(StringUtils.isEmpty(tenantName)) {
			tenantName = request.getParameter(SpringDataJPAMultitenantConstants.TENANT_ID);
			log.debug("Current Tenant {} from getParameter", tenantName);
		}
		
		if (!StringUtils.isEmpty(tenantName)) {
			TenantContext.setCurrentTenant(tenantName);
		} else {
			TenantContext.setCurrentTenant(SpringDataJPAMultitenantConstants.DEFAULT_TENANT_ID);
		}
		
		return true;
	}
	@Override
	public void preHandle(WebRequest request) throws Exception {
		String tenantName = request.getParameter(SpringDataJPAMultitenantConstants.TENANT_ID);
		TenantContext.setCurrentTenant(tenantName);
		log.debug("WebRequest: Current Tenant {}", tenantName);
	}

	@Override
	public void afterCompletion(WebRequest arg0, Exception arg1) throws Exception {
	}

	@Override
	public void postHandle(WebRequest arg0, ModelMap arg1) throws Exception {
	}
}