package com.EmployeeManagementSystem.ServiceImplementions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagementSystem.Entity.BranchEntity;
import com.EmployeeManagementSystem.Entity.DepartmentEntity;
import com.EmployeeManagementSystem.Entity.OrganizationEntity;
import com.EmployeeManagementSystem.Entity.SubDepartmentEntity;
import com.EmployeeManagementSystem.Exception.BranchNotFoundException;
import com.EmployeeManagementSystem.Exception.DepartmentNotFoundException;
import com.EmployeeManagementSystem.Exception.DuplicateDataException;
import com.EmployeeManagementSystem.Exception.OrganizationNotFoundException;
import com.EmployeeManagementSystem.Repository.BranchRepository;
import com.EmployeeManagementSystem.Repository.DepartmentRepository;
import com.EmployeeManagementSystem.Repository.OrganizationRepository;
import com.EmployeeManagementSystem.Service.DepartmentService;
import com.EmployeeManagementSystem.dto.DepartmentDto;
import com.EmployeeManagementSystem.dto.SubDepartmentDto;

@Service
public class DepartmentServiceImplemention implements DepartmentService {

	     @Autowired
	    private DepartmentRepository departmentRepository;

	    @Autowired
	    private BranchRepository branchRepository;

	    @Autowired
	    private OrganizationRepository organizationRepository;

	    @Override
	    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity, Long organizationId, Integer branchId) {
	        OrganizationEntity organization = organizationRepository.findById(organizationId)
	                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));
	        
	        BranchEntity branch = branchRepository.findById(branchId)
	                .orElseThrow(() -> new BranchNotFoundException(branchId));
	        
	        departmentEntity.setOrganization(organization);
	        departmentEntity.setBranch(branch);
	        
	        
	        return departmentRepository.save(departmentEntity);
	    }
	    
	    
	  
	    
	    @Override
	    public DepartmentDto getDepartmentDtoById(int departmentId) {
	        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId)
	                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));
	        
	        DepartmentDto departmentDto = new DepartmentDto();
	        departmentDto.setDepartmentId(departmentEntity.getDepartmentId());
	        departmentDto.setDepartmentname(departmentEntity.getDepartmentname());
	        departmentDto.setDepartmentDescription(departmentEntity.getDepartmentDescription());
	        departmentDto.setOrganizationId(departmentEntity.getOrganization().getOrganizationId());
	        departmentDto.setBranchId(departmentEntity.getBranch().getBranchId());
	        
	        return departmentDto;
	    }
//	    @Override
//		public List<DepartmentDto> getAllDepartments() {
//		    List<DepartmentEntity> departments = departmentRepository.findAll();
//		    return departments.stream()
//		            .map(this::mapToDto)
//		            .collect(Collectors.toList());
//		}
//
//		private DepartmentDto mapToDto(DepartmentEntity department) {
//		    DepartmentDto departmentDto = new DepartmentDto();
//		    departmentDto.setDepartmentId(department.getDepartmentId());
//		    departmentDto.setDepartmentname(department.getDepartmentname());
//		    departmentDto.setDepartmentDescription(department.getDepartmentDescription());
//		    departmentDto.setOrganizationId(department.getOrganization().getOrganizationId());
//		    departmentDto.setBranchId(department.getBranch().getBranchId());
//		    return departmentDto;
//		}

		@Override
	    public DepartmentEntity updateDepartment(Long organizationId, Integer branchId, Integer departmentId, DepartmentDto departmentDto) {
	        OrganizationEntity organization = organizationRepository.findById(organizationId)
	                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));

	        BranchEntity branch = branchRepository.findById(branchId)
	                .orElseThrow(() -> new BranchNotFoundException(branchId));

	        DepartmentEntity existingDepartment = departmentRepository.findById(departmentId)
	                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

	        if (!existingDepartment.getOrganization().equals(organization) || !existingDepartment.getBranch().equals(branch)) {
	            throw new DepartmentNotFoundException(departmentId);
	        }

	        existingDepartment.setDepartmentname(departmentDto.getDepartmentname());
	        existingDepartment.setDepartmentDescription(departmentDto.getDepartmentDescription());
	        existingDepartment.setBranch(branch);
	        existingDepartment.setOrganization(organization);

	        return departmentRepository.save(existingDepartment);
	    }
		
//		@Override
//	    public List<DepartmentDto> getDepartmentsByBranchId(Integer branchId) {
//	        BranchEntity branch = branchRepository.findById(branchId)
//	                .orElseThrow(() -> new BranchNotFoundException(branchId));
//	        
//	        List<DepartmentEntity> departments = departmentRepository.findByBranch(branch);
//	        
//	        return departments.stream()
//	                .map(this::mapToDto)
//	                .collect(Collectors.toList());
//	    }
//
//	    private DepartmentDto mapToDto(DepartmentDto department) {
//	        DepartmentDto departmentDto = new DepartmentDto();
//	        departmentDto.setDepartmentId(department.getDepartmentId());
//	        departmentDto.setDepartmentname(department.getDepartmentname());
//	        departmentDto.setDepartmentDescription(department.getDepartmentDescription());
//	        departmentDto.setOrganizationId(department.getOrganizationId());
//	        departmentDto.setBranchId(department.getBranchId());
//	        return departmentDto;
//	    }
		
		
		@Override
	    public List<DepartmentDto> getAllDepartments() {
	        List<DepartmentEntity> departments = departmentRepository.findAll();
	        return departments.stream()
	                .map(this::mapToDto)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<DepartmentDto> getDepartmentsByBranchId(Integer branchId) {
	        BranchEntity branch = branchRepository.findById(branchId)
	                .orElseThrow(() -> new BranchNotFoundException(branchId));
	        
	        List<DepartmentEntity> departments = departmentRepository.findByBranch(branch);
	        
	        return departments.stream()
	                .map(this::mapToDto)
	                .collect(Collectors.toList());
	    }

	    private DepartmentDto mapToDto(DepartmentEntity department) {
	        DepartmentDto departmentDto = new DepartmentDto();
	        departmentDto.setDepartmentId(department.getDepartmentId());
	        departmentDto.setDepartmentname(department.getDepartmentname());
	        departmentDto.setDepartmentDescription(department.getDepartmentDescription());
	        departmentDto.setOrganizationId(department.getOrganization().getOrganizationId());
	        departmentDto.setBranchId(department.getBranch().getBranchId());

	        

	        List<SubDepartmentDto> subDepartments = department.getSubDepartments().stream()
	                .map(this::mapSubDepartmentToDto)
	                .collect(Collectors.toList());
	        departmentDto.setSubDepartments(subDepartments);

	        return departmentDto;
	    }

	    private SubDepartmentDto mapSubDepartmentToDto(SubDepartmentEntity subDepartment) {
	        SubDepartmentDto subDepartmentDto = new SubDepartmentDto();
	        subDepartmentDto.setSubDepartmentId(subDepartment.getSubDepartmentId());
	        subDepartmentDto.setSubDepartmentName(subDepartment.getSubDepartmentName());
	        subDepartmentDto.setSubDepartmentDescription(subDepartment.getSubDepartmentDescription());
	        subDepartmentDto.setDepartmentId(subDepartment.getParentDepartment().getDepartmentId());
	        return subDepartmentDto;
	    }



	    @Override
	    public String deleteById(int departmentId) {
	        if (!departmentRepository.existsById(departmentId)) {
	            throw new DepartmentNotFoundException(departmentId);
	        }

	        departmentRepository.deleteById(departmentId);
	        return "Department with ID " + departmentId + " has been successfully deleted!";
	    }
		
}
