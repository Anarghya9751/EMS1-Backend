package com.Ems.Service;

import java.util.List;

import com.Ems.Entity.BranchEntity;
import com.Ems.dto.BranchForm;



public interface BranchService
{
	
    public String createBranch(Long organizationId, BranchEntity branchEntity) ;

        String deleteBranch(Integer branchId);

	    
	    public BranchEntity updateBranch(Long organizationId, Integer branchId, BranchForm branchDto) ;

	    public List<BranchEntity> getAllBranches() ;


	    public BranchEntity getBranchWithDepartments(Integer branchId) ;

	    

}
