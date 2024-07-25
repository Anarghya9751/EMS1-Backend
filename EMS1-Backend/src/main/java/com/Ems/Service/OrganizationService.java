package com.EmployeeManagementSystem.Service;

import java.util.List;

import com.EmployeeManagementSystem.Entity.OrganizationEntity;
import com.EmployeeManagementSystem.dto.OrganizationForm;



public interface OrganizationService {

	
	 
	 
	 
	 public OrganizationEntity AddEntity(OrganizationEntity entity);
		
	 OrganizationEntity getById(Long Id);
	 
	 
	 OrganizationEntity updateOrganization(Long organizationId, OrganizationForm updatedOrganization);

	 
	 List<OrganizationEntity>getAllOrganizations();
	 
	 public String deleteById(Long Id);



}
