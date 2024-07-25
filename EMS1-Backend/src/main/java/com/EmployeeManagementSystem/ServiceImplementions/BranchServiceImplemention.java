package com.EmployeeManagementSystem.ServiceImplementions;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagementSystem.Entity.BranchEntity;
import com.EmployeeManagementSystem.Entity.DepartmentEntity;
import com.EmployeeManagementSystem.Entity.OrganizationEntity;
import com.EmployeeManagementSystem.Entity.SubDepartmentEntity;
import com.EmployeeManagementSystem.Exception.BranchAlreadyExistsException;
import com.EmployeeManagementSystem.Exception.BranchNotFoundException;
import com.EmployeeManagementSystem.Exception.DuplicateDataException;
import com.EmployeeManagementSystem.Exception.OrganizationNotFoundException;
import com.EmployeeManagementSystem.Repository.BranchRepository;
import com.EmployeeManagementSystem.Repository.OrganizationRepository;
import com.EmployeeManagementSystem.Service.BranchService;
import com.EmployeeManagementSystem.dto.BranchForm;
import com.EmployeeManagementSystem.dto.DepartmentDto;


@Service
public class BranchServiceImplemention implements BranchService {

	
	    @Autowired
	    private BranchRepository branchRepository;
	    
	    @Autowired
	    private OrganizationRepository organizationRepository;
	    
//	    @Override
//	    public String createBranch(Long organizationId, BranchEntity branchEntity) {
//	        if (branchEntity.getBranchId() != null && branchRepository.existsById(branchEntity.getBranchId())) {
//	            throw new BranchAlreadyExistsException(branchEntity.getBranchId());
//	        }
//	        OrganizationEntity organization = organizationRepository.findById(organizationId)
//	                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));
//	        branchEntity.setOrganization(organization);
//	        branchRepository.save(branchEntity);
//	        return "Branch created successfully with ID: " + branchEntity.getBranchId() + " for Organization ID: " + organizationId;
//	    }

	    
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
		    public List<BranchForm> getBranchesByOrganizationId(Long organizationId) {
		        OrganizationEntity organization = organizationRepository.findById(organizationId)
		                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));
		        
		        List<BranchEntity> branches = branchRepository.findByOrganization(organization);
		        
		        return branches.stream()
		                .map(this::mapToDto)
		                .collect(Collectors.toList());
		    }

		    private BranchForm mapToDto(BranchEntity branch) {
		        BranchForm branchDto = new BranchForm();
		        branchDto.setBranchId(branch.getBranchId());
		        branchDto.setBranchName(branch.getBranchName());
		        branchDto.setBranchDescription(branch.getBranchDescription());
		        branchDto.setBranchAddress(branch.getBranchAddress());
		        branchDto.setBranchContactNumber(branch.getBranchContactNumber());
		        branchDto.setOrganizationId(branch.getOrganization().getOrganizationId());
		        return branchDto;
		    }
		 
		
//		    @Override
//		    public BranchForm getBranchById(Integer branchId) {
//		        Optional<BranchEntity> optionalBranch = branchRepository.findById(branchId);
//		        if (optionalBranch.isPresent()) {
//		            BranchEntity branch = optionalBranch.get();
//		            BranchForm branchDTO = new BranchForm();
//
//		            branchDTO.setBranchId(branch.getBranchId());
//		            branchDTO.setBranchName(branch.getBranchName());
//		            branchDTO.setBranchDescription(branch.getBranchDescription());
//		            branchDTO.setBranchAddress(branch.getBranchAddress());
//		            branchDTO.setBranchContactNumber(branch.getBranchContactNumber());
//		            branchDTO.setOrganizationId(branch.getOrganization().getOrganizationId());
//
//		            List<DepartmentDto> departments = branch.getDepartments().stream()
//		                    .map(this::mapDepartmentToDto)
//		                    .collect(Collectors.toList());
//		            branchDTO.setDepartments(departments); 
//		            return branchDTO; 
//		        }
//		        return null; 
//		    }
//
//		    @Override
//		    public List<BranchForm> getAllBranches() {
//		        List<BranchEntity> branches = branchRepository.findAll();
//		        return branches.stream()
//		                .map(this::mapToDTOWithDepartments) 
//		                .collect(Collectors.toList());
//		    }
//
//		    private BranchForm mapToDTOWithDepartments(BranchEntity branch) {
//		        BranchForm branchDTO = new BranchForm();
//		        branchDTO.setBranchId(branch.getBranchId());
//		        branchDTO.setBranchName(branch.getBranchName());
//		        branchDTO.setBranchDescription(branch.getBranchDescription());
//		        branchDTO.setBranchAddress(branch.getBranchAddress());
//		        branchDTO.setBranchContactNumber(branch.getBranchContactNumber());
//		        branchDTO.setOrganizationId(branch.getOrganization().getOrganizationId());
//
//		        List<DepartmentDto> departments = branch.getDepartments().stream()
//		                .map(this::mapDepartmentToDto)
//		                .collect(Collectors.toList());
//		        branchDTO.setDepartments(departments); 
//
//		        return branchDTO; 
//		    }
//
//		    private DepartmentDto mapDepartmentToDto(DepartmentEntity department) {
//		        DepartmentDto departmentDto = new DepartmentDto();
//		        departmentDto.setDepartmentId(department.getDepartmentId());
//		        departmentDto.setDepartmentname(department.getDepartmentname());
//		        departmentDto.setDepartmentDescription(department.getDepartmentDescription());
//		        return departmentDto;
//		    }

		    
		    @Override
		    public BranchForm getBranchById(Integer branchId) {
		        Optional<BranchEntity> optionalBranch = branchRepository.findById(branchId);
		        if (optionalBranch.isPresent()) {
		            BranchEntity branch = optionalBranch.get();
		            BranchForm branchDTO = new BranchForm();

		            branchDTO.setBranchId(branch.getBranchId());
		            branchDTO.setBranchName(branch.getBranchName());
		            branchDTO.setBranchDescription(branch.getBranchDescription());
		            branchDTO.setBranchAddress(branch.getBranchAddress());
		            branchDTO.setBranchContactNumber(branch.getBranchContactNumber());
		            branchDTO.setOrganizationId(branch.getOrganization().getOrganizationId());

		            List<DepartmentDto> departments = branch.getDepartments().stream()
		                    .map(this::mapDepartmentToDto)
		                    .collect(Collectors.toList());
		            branchDTO.setDepartments(departments);

		            return branchDTO; 
		        }
		        return null; 
		    }

		    @Override
		    public List<BranchForm> getAllBranches() {
		        List<BranchEntity> branches = branchRepository.findAll();
		        return branches.stream()
		                .map(this::mapToDTOWithDepartments) 
		                .collect(Collectors.toList());
		    }

		    private BranchForm mapToDTOWithDepartments(BranchEntity branch) {
		        BranchForm branchDTO = new BranchForm();
		        branchDTO.setBranchId(branch.getBranchId());
		        branchDTO.setBranchName(branch.getBranchName());
		        branchDTO.setBranchDescription(branch.getBranchDescription());
		        branchDTO.setBranchAddress(branch.getBranchAddress());
		        branchDTO.setBranchContactNumber(branch.getBranchContactNumber());
		        branchDTO.setOrganizationId(branch.getOrganization().getOrganizationId());

		        List<DepartmentDto> departments = branch.getDepartments().stream()
		                .map(this::mapDepartmentToDto)
		                .collect(Collectors.toList());
		        branchDTO.setDepartments(departments);

		        return branchDTO; 
		    }

		    private DepartmentDto mapDepartmentToDto(DepartmentEntity department) {
		        DepartmentDto departmentDto = new DepartmentDto();
		        departmentDto.setDepartmentId(department.getDepartmentId());
		        departmentDto.setDepartmentname(department.getDepartmentname());
		        departmentDto.setDepartmentDescription(department.getDepartmentDescription());

		        departmentDto.setOrganizationId(department.getOrganization().getOrganizationId());
		        departmentDto.setBranchId(department.getBranch().getBranchId());

		        


		        if (department.getSubDepartments() != null) {
		            List<Long> subDepartmentIds = department.getSubDepartments().stream()
		                    .map(SubDepartmentEntity::getSubDepartmentId)
		                    .collect(Collectors.toList());
		            departmentDto.setSubDepartmentId(subDepartmentIds);; 


		            
		        }

		        return departmentDto;

		    }
		   

		}
	   
	


