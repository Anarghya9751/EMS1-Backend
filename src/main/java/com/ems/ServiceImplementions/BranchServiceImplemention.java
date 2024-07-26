package com.ems.ServiceImplementions;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.Entity.BranchEntity;
import com.ems.Entity.OrganizationEntity;
import com.ems.Exception.BranchAlreadyExistsException;
import com.ems.Exception.BranchNotFoundException;
import com.ems.Exception.OrganizationNotFoundException;
import com.ems.Repo.BranchRepository;
import com.ems.Repo.OrganizationRepository;
import com.ems.Service.BranchService;
import com.ems.dto.BranchForm;

@Service
public class BranchServiceImplemention implements BranchService {

	
	    @Autowired
	    private BranchRepository branchRepository;
	    
	    @Autowired
	    private OrganizationRepository organizationRepository;
	    
	    @Override
	    public String createBranch(Long organizationId, BranchEntity branchEntity) {
	        if (branchEntity.getBranchId() != null && branchRepository.existsById(branchEntity.getBranchId())) {
	            throw new BranchAlreadyExistsException(branchEntity.getBranchId());
	        }
	        OrganizationEntity organization = organizationRepository.findById(organizationId)
	                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));
	        branchEntity.setOrganization(organization);
	        branchRepository.save(branchEntity);
	        return "Branch created successfully with ID: " + branchEntity.getBranchId() + " for Organization ID: " + organizationId;
	    }



	    @Override
	    public String deleteBranch(Integer branchId) 
	    {
	        if (!branchRepository.existsById(branchId))
	        {
	            throw new BranchNotFoundException(branchId);
	        }
	        branchRepository.deleteById(branchId);
	        return "Branch deleted successfully with ID: " + branchId;
	    }
	    


		@Override
		public BranchForm getBranchById(Integer branchId) {
			Optional<BranchEntity> optionalBranch = branchRepository.findById(branchId);
			if (optionalBranch.isPresent()) {
				BranchEntity branch = optionalBranch.get();
				BranchForm branchDTO = new BranchForm();

				branchDTO.setBranchName(branch.getBranchName());
				branchDTO.setBranchDescription(branch.getBranchDescription());
				branchDTO.setBranchId(branch.getBranchId());
				branchDTO.setBranchAddress(branch.getBranchAddress());
				branchDTO.setBranchContactNumber(branch.getBranchContactNumber());
				branchDTO.setOrganizationId(branch.getOrganization().getOrganizationId());
				branchDTO.setDepartments(branch.getDepartments());
				return branchDTO;
			}
			return null;
		}



		@Override
		public List<BranchForm> getAllBranches() {
			List<BranchEntity> branches = branchRepository.findAll();
			return branches.stream().map(this::mapToDTO).collect(Collectors.toList());
		}

		private BranchForm mapToDTO(BranchEntity branch) {
			BranchForm branchDTO = new BranchForm();
			branchDTO.setBranchName(branch.getBranchName());
			branchDTO.setBranchDescription(branch.getBranchDescription());
			branchDTO.setBranchId(branch.getBranchId());
			branchDTO.setBranchAddress(branch.getBranchAddress());
			branchDTO.setBranchContactNumber(branch.getBranchContactNumber());
			branchDTO.setOrganizationId(branch.getOrganization().getOrganizationId());
			return branchDTO;
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
		
		
	   
	}


