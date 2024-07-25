package com.EmployeeManagementSystem.Exception;

public class BranchNotFoundException extends RuntimeException {
    public BranchNotFoundException(Integer branchId) {
        super("Branch with ID " + branchId + " not found.");
    }
}


