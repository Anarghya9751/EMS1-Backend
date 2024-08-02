package com.Ems.Service;

import java.util.List;

import com.Ems.Entity.OrganizationEntity;
import com.Ems.dto.OrganizationForm;



public interface OrganizationService {

	
	 
	 
	 
	public OrganizationEntity addEntity(OrganizationForm form) ;
		
	 OrganizationEntity getById(Long organizationId);
	 
	 
	 OrganizationEntity updateOrganization(Long organizationId, OrganizationForm updatedOrganization);

	 
	 List<OrganizationEntity>getAllOrganizations();
	 
	 public String deleteById(Long organizationId);



}
