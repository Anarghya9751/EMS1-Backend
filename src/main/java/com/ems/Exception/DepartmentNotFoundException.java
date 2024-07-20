package com.ems.Exception;

public class DepartmentNotFoundException extends RuntimeException{
	public DepartmentNotFoundException(Long departmentId) {
        super("Organization with ID " + departmentId + " not found.");
    }

}
