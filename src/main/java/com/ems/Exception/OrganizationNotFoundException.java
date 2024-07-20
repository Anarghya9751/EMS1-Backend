package com.ems.Exception;
public class OrganizationNotFoundException extends RuntimeException {
	    public OrganizationNotFoundException(Long organizationId) {
	        super("Organization with ID " + organizationId + " not found.");
	    }
}
