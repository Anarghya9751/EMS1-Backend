package com.ems.Service;

import java.util.List;

import com.ems.Entity.OrganizationEntity;
import com.ems.dto.OrganizationForm;


public interface OrganizationService {

	public OrganizationEntity AddEntity(OrganizationEntity entity);
	
	 OrganizationEntity getById(Long Id);
	 
	 
	 OrganizationEntity updateOrganization(Long organizationId, OrganizationForm updatedOrganization);

	 
	 List<OrganizationEntity>getAllOrganizations();
	 
	 public String deleteById(Long Id);


}
