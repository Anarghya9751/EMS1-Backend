package com.ems.Exception;


public class SubDepartmentNotFoundException extends RuntimeException {

	public SubDepartmentNotFoundException(Long subDepartmentId) {
		super("subDepartment with Id"+subDepartmentId + "not found");
		
	}

}
