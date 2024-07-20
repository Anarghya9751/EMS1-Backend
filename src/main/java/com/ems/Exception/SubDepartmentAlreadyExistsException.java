package com.ems.Exception;

public class SubDepartmentAlreadyExistsException extends RuntimeException {
    public SubDepartmentAlreadyExistsException(Long subdepartmentId) {
        super("Branch with ID " + subdepartmentId + " already exists.");
    } 
}

