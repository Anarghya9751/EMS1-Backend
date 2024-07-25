package com.EmployeeManagementSystem.Exception;
public class OrganizationNotFoundException extends RuntimeException {
	    public OrganizationNotFoundException(Long organizationId) {
	        super("Organization with ID " + organizationId + " not found.");
	    }
}
