package com.EmployeeManagementSystem.Service;

import java.util.List;

import com.EmployeeManagementSystem.Entity.BranchEntity;
import com.EmployeeManagementSystem.dto.BranchForm;



public interface BranchService
{
	
        public String createBranch(Long organizationId, BranchEntity branchEntity) ;

        String deleteBranch(Integer branchId);

	    
	    public BranchEntity updateBranch(Long organizationId, Integer branchId, BranchForm branchDto) ;


	    BranchForm getBranchById(Integer branchId);

	    List<BranchForm> getAllBranches();
	    
	    
	    public List<BranchForm> getBranchesByOrganizationId(Long organizationId);

}
