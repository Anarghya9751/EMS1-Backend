package com.Ems.ServiceImplementions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ems.Entity.BranchEntity;
import com.Ems.Entity.DepartmentEntity;
import com.Ems.Entity.OrganizationEntity;
import com.Ems.Entity.SubDepartmentEntity;
import com.Ems.Exception.BranchNotFoundException;
import com.Ems.Exception.DepartmentNotFoundException;
import com.Ems.Exception.DuplicateDataException;
import com.Ems.Exception.OrganizationNotFoundException;
import com.Ems.Repository.BranchRepository;
import com.Ems.Repository.DepartmentRepository;
import com.Ems.Repository.OrganizationRepository;
import com.Ems.Service.DepartmentService;
import com.Ems.dto.DepartmentDto;
import com.Ems.dto.SubDepartmentDto;

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
		




	    @Override
	    public String deleteById(int departmentId) {
	        if (!departmentRepository.existsById(departmentId)) {
	            throw new DepartmentNotFoundException(departmentId);
	        }

	        departmentRepository.deleteById(departmentId);
	        return "Department with ID " + departmentId + " has been successfully deleted!";
	    }
	   
	    @Override
		public DepartmentDto getDepartmentById(int departmentId) {
			DepartmentEntity departmentEntity = departmentRepository.findById(departmentId)
	                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));
	        
	        DepartmentDto departmentDto = new DepartmentDto();
	        departmentDto.setDepartmentId(departmentEntity.getDepartmentId());
	        departmentDto.setDepartmentname(departmentEntity.getDepartmentname());
	        departmentDto.setDepartmentDescription(departmentEntity.getDepartmentDescription());
	        departmentDto.setOrganizationId(departmentEntity.getOrganization().getOrganizationId());
	        departmentDto.setBranchId(departmentEntity.getBranch().getBranchId());
	        departmentDto.setSubdepartments(departmentEntity.getSubDepartments());
	        
	        return departmentDto;
	    }
	    @Override
		public List<DepartmentDto> getAllDepartments() {
			List<DepartmentEntity> departments = departmentRepository.findAll();
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
	        departmentDto.setSubdepartments(department.getSubDepartments());

		    return departmentDto;
		}


		public DepartmentEntity getDepartmentById1(Integer departmentId) {
		    return departmentRepository.findById(departmentId)
		            .orElseThrow(() -> new DepartmentNotFoundException(departmentId));
		}

}
