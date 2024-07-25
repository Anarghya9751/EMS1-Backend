package com.EmployeeManagementSystem.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SubDepartmentNotFoundException extends RuntimeException {

    public SubDepartmentNotFoundException(Long subDepartmentId) {
        super("Sub-department not found with ID: " + subDepartmentId);
    }
}
