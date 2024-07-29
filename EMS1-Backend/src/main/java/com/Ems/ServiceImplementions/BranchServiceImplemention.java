package com.Ems.ServiceImplementions;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ems.Entity.BranchEntity;
import com.Ems.Entity.DepartmentEntity;
import com.Ems.Entity.OrganizationEntity;
import com.Ems.Entity.SubDepartmentEntity;
import com.Ems.Exception.BranchAlreadyExistsException;
import com.Ems.Exception.BranchDeptNotFoundException;
import com.Ems.Exception.BranchNotFoundException;
import com.Ems.Exception.DuplicateDataException;
import com.Ems.Exception.OrganizationNotFoundException;
import com.Ems.Repository.BranchRepository;
import com.Ems.Repository.OrganizationRepository;
import com.Ems.Service.BranchService;
import com.Ems.dto.BranchForm;
import com.Ems.dto.DepartmentDto;


@Service
public class BranchServiceImplemention implements BranchService {

	
	    @Autowired
	    private BranchRepository branchRepository;
	    
	    @Autowired
	    private OrganizationRepository organizationRepository;
	    

	    
	  
	    
	    @Override
	    public String createBranch(Long organizationId, BranchEntity branchEntity) {
	        if (branchEntity.getBranchId() != null && branchRepository.existsById(branchEntity.getBranchId())) {
	            throw new DuplicateDataException("Branch with ID " + branchEntity.getBranchId() + " already exists.");
	        }

	        Optional<BranchEntity> existingBranch = branchRepository.findByBranchNameAndBranchDescriptionAndBranchAddressAndBranchContactNumber(
	                branchEntity.getBranchName(),
	                branchEntity.getBranchDescription(),
	                branchEntity.getBranchAddress(),
	                branchEntity.getBranchContactNumber()
	        );

	        if (existingBranch.isPresent()) {
	            throw new DuplicateDataException("Branch with the same Branchname, description, address, and contact number already exists.please Check Onces");
	        }

	        branchRepository.save(branchEntity);
	        return "Branch created successfully with ID: " + branchEntity.getBranchId() + " for Organization ID: " + organizationId;
	    }
	
	    @Override
	    public BranchEntity getBranchWithDepartments(Integer branchId) 
	    {
	        return branchRepository.findById(branchId)
	                .orElseThrow(() -> new BranchDeptNotFoundException("Branch not found with ID: " + branchId));
	    }


	    @Override
	    public String deleteBranch(Integer branchId) 
	    {
	        if (!branchRepository.existsById(branchId))
	        {
	            throw new BranchNotFoundException(branchId);
	        }
	        branchRepository.deleteById(branchId);
	        return "Branch deleted successfully. with ID: " + branchId;
	    }
	    
	    
	    



	    
		
		 @Override
		    public BranchEntity updateBranch(Long organizationId, Integer branchId, BranchForm branchDto) {
		        OrganizationEntity organization = organizationRepository.findById(organizationId)
		                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));

		        BranchEntity existingBranch = branchRepository.findById(branchId)
		                .orElseThrow(() -> new BranchNotFoundException(branchId));

		        if (!existingBranch.getOrganization().equals(organization)) {
		            throw new BranchNotFoundException(branchId);
		        }

		        existingBranch.setBranchName(branchDto.getBranchName());
		        existingBranch.setBranchDescription(branchDto.getBranchDescription());
		        existingBranch.setBranchAddress(branchDto.getBranchAddress());
		        existingBranch.setBranchContactNumber(branchDto.getBranchContactNumber());

		        return branchRepository.save(existingBranch);
		    }
		 
		 
		 
		 @Override
		    public List<BranchEntity> getAllBranches() {
		        return branchRepository.findAll();
		    }


		}
	   
	


