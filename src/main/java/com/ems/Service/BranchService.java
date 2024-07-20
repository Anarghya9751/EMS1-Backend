package com.ems.Service;

import java.util.List;

import com.ems.Entity.BranchEntity;
import com.ems.dto.BranchForm;

public interface BranchService
{
        public String createBranch(Long organizationId, BranchEntity branchEntity) ;

        String deleteBranch(Integer branchId);
	    
        BranchEntity updateBranch(Long organizationId, Integer branchId, BranchForm branchDto);

	    BranchForm getBranchById(Integer branchId);

	    List<BranchForm> getAllBranches();
}
