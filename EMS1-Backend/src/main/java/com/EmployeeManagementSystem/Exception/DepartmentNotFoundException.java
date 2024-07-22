package com.EmployeeManagementSystem.Exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(int departmentId) {
        super("Department not found with ID: " + departmentId);
    }
}
