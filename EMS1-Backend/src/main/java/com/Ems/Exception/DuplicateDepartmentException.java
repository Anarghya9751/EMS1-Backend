package com.Ems.Exception;

public class DuplicateDepartmentException extends RuntimeException {
    public DuplicateDepartmentException(String departmentName, String departmentDescription) {
        super(String.format("Department with name '%s' and description '%s' already exists.", departmentName, departmentDescription));
    }
}