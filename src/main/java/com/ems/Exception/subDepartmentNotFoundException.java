package com.ems.Exception;

public class subDepartmentNotFoundException extends RuntimeException {

	public subDepartmentNotFoundException(Long subDepartmentId) {
		super("subDepartment with Id"+subDepartmentId + "not found");
		
	}

}
