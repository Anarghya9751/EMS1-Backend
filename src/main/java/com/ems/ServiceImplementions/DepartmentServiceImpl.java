package com.ems.ServiceImplementions;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.Entity.BranchEntity;
import com.ems.Entity.DepartmentEntity;
import com.ems.Entity.OrganizationEntity;
import com.ems.Exception.BranchNotFoundException;
import com.ems.Exception.DepartmentNotFoundException;
import com.ems.Exception.OrganizationNotFoundException;
import com.ems.Repo.BranchRepository;
import com.ems.Repo.DepartmentRepository;
import com.ems.Repo.OrganizationRepository;
import com.ems.Service.DepartmentService;
import com.ems.dto.DepartmentDto;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepo;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private OrganizationRepository  repo;

	@Override
	public DepartmentEntity AddDepartment(DepartmentEntity departmentEntity,Integer branchId,Long organizationId) {
		 OrganizationEntity organization = repo.findById(organizationId)
	                .orElseThrow(() -> new OrganizationNotFoundException(organizationId));
	        
	        BranchEntity branch = branchRepository.findById(branchId)
	                .orElseThrow(() -> new BranchNotFoundException(branchId));
	        
	        departmentEntity.setOrganization(organization);
	        departmentEntity.setBranch(branch);
	        
	        return departmentRepo.save(departmentEntity);
	    }

	@Override
	public DepartmentDto getDepartmentById(Long departmentId) {
		DepartmentEntity departmentEntity = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));
        
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentId(departmentEntity.getDepartmentId());
        departmentDto.setDepartmentName(departmentEntity.getDepartmentName());
        departmentDto.setDepartmentDescription(departmentEntity.getDepartmentDescription());
        departmentDto.setOrganizationId(departmentEntity.getOrganization().getOrganizationId());
        departmentDto.setBranchId(departmentEntity.getBranch().getBranchId());
        departmentDto.setSubdepartments(departmentEntity.getSubdepartments());
        
        return departmentDto;
    }

	
	@Override
	public String deleteById(Long departmentId) {
		Optional<DepartmentEntity> optional = departmentRepo.findById(departmentId);
		if (optional.isPresent()) {
			departmentRepo.deleteById(departmentId);
			return "deleted successfully";
			
		}
		return "not deleted successfully";
	}

	@Override
	public DepartmentEntity updateDepartment(DepartmentEntity departmentEntity, Long departmentId) {
		DepartmentEntity existingDepartment = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

        existingDepartment.setDepartmentName(departmentEntity.getDepartmentName());
        existingDepartment.setDepartmentDescription(departmentEntity.getDepartmentDescription());
        existingDepartment.setBranch(departmentEntity.getBranch());
        existingDepartment.setOrganization(departmentEntity.getOrganization());

        return departmentRepo.save(existingDepartment);
    }

	@Override
	public List<DepartmentDto> getAllDepartments() {
		List<DepartmentEntity> departments = departmentRepo.findAll();
		return departments.stream()
				.map(this::mapToDto)
	            .collect(Collectors.toList());
	}

	private DepartmentDto mapToDto(DepartmentEntity department) {
	    DepartmentDto departmentDto = new DepartmentDto();
	    departmentDto.setDepartmentId(department.getDepartmentId());
	    departmentDto.setDepartmentName(department.getDepartmentName());
	    departmentDto.setDepartmentDescription(department.getDepartmentDescription());
	    departmentDto.setOrganizationId(department.getOrganization().getOrganizationId());
	    departmentDto.setBranchId(department.getBranch().getBranchId());
	    return departmentDto;
	}


	

	
}
