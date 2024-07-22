package com.EmployeeManagementSystem.Exception;

public class BranchAlreadyExistsException extends RuntimeException {
    public BranchAlreadyExistsException(Integer branchId) {
        super("Branch with ID " + branchId + " already exists.");
    }
}
