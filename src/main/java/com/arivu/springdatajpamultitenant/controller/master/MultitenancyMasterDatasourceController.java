package com.arivu.springdatajpamultitenant.controller.master;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.arivu.springdatajpamultitenant.constant.SpringDataJPAMultitenantConstants;
import com.arivu.springdatajpamultitenant.context.TenantContext;
import com.arivu.springdatajpamultitenant.controller.AbstractController;
import com.arivu.springdatajpamultitenant.entity.master.MultitenantcyMasterDatasourceEntity;
import com.arivu.springdatajpamultitenant.jpa.TenantDataSource;
import com.arivu.springdatajpamultitenant.service.master.IMultitenancyMasterDatasourceService;

@Controller
public class MultitenancyMasterDatasourceController  extends AbstractController {
	
	@Autowired
	private IMultitenancyMasterDatasourceService multitenancyMasterDatasourceService;
	
	@Autowired
	public TenantDataSource tenantDataSource;
		
	private List<MultitenantcyMasterDatasourceEntity> getAllMultitenancyMasterDatasources(){ 
		return multitenancyMasterDatasourceService.findAll();		
	}
		
	@RequestMapping(SpringDataJPAMultitenantConstants.ROOT_PATH)
	public String index(Model model) {
		List<MultitenantcyMasterDatasourceEntity> multitenantcyMasterDatasourcesList = getAllMultitenancyMasterDatasources();
		tenantDataSource.loadAllTenants(multitenantcyMasterDatasourcesList);
		for(MultitenantcyMasterDatasourceEntity multitenantcyMasterDatasourceEntity :multitenantcyMasterDatasourcesList ) {
			logger.debug("dataSourcekey {}",multitenantcyMasterDatasourceEntity.getDataSourceKey());
		}
		model.addAttribute(SpringDataJPAMultitenantConstants.TENANTS, multitenantcyMasterDatasourcesList);
		model.addAttribute(SpringDataJPAMultitenantConstants.TENANT, new MultitenantcyMasterDatasourceEntity());
		return SpringDataJPAMultitenantConstants.INDEX;
	}
	
	@RequestMapping(value = SpringDataJPAMultitenantConstants.ADD_TENANT_PATH, method = RequestMethod.GET)
	public String addTenant(Model model) {
		logger.debug("Adding new Tenant");
		model.addAttribute(SpringDataJPAMultitenantConstants.TENANT, new MultitenantcyMasterDatasourceEntity());
		return SpringDataJPAMultitenantConstants.ADD_TENANT;
	}
	
	@RequestMapping(value = SpringDataJPAMultitenantConstants.ADD_TENANT_PATH, method = RequestMethod.POST)
	@Transactional
	public String addTenant(@ModelAttribute MultitenantcyMasterDatasourceEntity tenant, Model model, @RequestParam(value="action", required=true) String action) throws SQLException {
		if(SpringDataJPAMultitenantConstants.SAVE.equals(action) && tenant != null) {
			multitenancyMasterDatasourceService.save(tenant);
			tenantDataSource.loadTenant(tenant);
			tenantDataSource.createSchema(tenant.getDataSourceKey());			
		}
		model.addAttribute(SpringDataJPAMultitenantConstants.TENANTS, getAllMultitenancyMasterDatasources());
		return SpringDataJPAMultitenantConstants.REDIRECT_PATH;
	}
	
	@RequestMapping(value = SpringDataJPAMultitenantConstants.DELETE_TENANT_PATH)
	@Transactional
	public String deleteTenant(@PathVariable String tenantName, Model model) {
		if(tenantName != null) {
			MultitenantcyMasterDatasourceEntity tenant=	multitenancyMasterDatasourceService.findByDataSourceKey(tenantName);
			multitenancyMasterDatasourceService.delete(tenant.getId());
			tenantDataSource.dropSchema(tenantName);
		} else {
			logger.debug("Tenant Name is missing in the request parameter");
		}
		model.addAttribute(SpringDataJPAMultitenantConstants.TENANTS, getAllMultitenancyMasterDatasources());
		return SpringDataJPAMultitenantConstants.REDIRECT_PATH;
	}

}
