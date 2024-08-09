package com.Ems.ServiceImplementions;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ems.Entity.DepartmentEntity;
import com.Ems.Entity.SubDepartmentEntity;
import com.Ems.Exception.DepartmentNotFoundException;
import com.Ems.Exception.DuplicateDataException;
import com.Ems.Exception.InvalidInputException;
import com.Ems.Exception.SubDepartmentNotFoundException;
import com.Ems.Repository.BranchRepository;
import com.Ems.Repository.DepartmentRepository;
import com.Ems.Repository.SubDepartmentRepository;
import com.Ems.Service.SubDepartmentService;
import com.Ems.dto.DepartmentDto;
import com.Ems.dto.SubDepartmentDto;


@Service
public class SubDepartmentServiceImpl implements SubDepartmentService {

	    @Autowired
	    private  SubDepartmentRepository subDepartmentRepository;
	    @Autowired
	    private  DepartmentRepository departmentRepository;
	    
	    @Autowired
	    private BranchRepository branchRepository;



	    
	    @Override
	    public SubDepartmentEntity saveSubDepartment(SubDepartmentDto subDepartmentDto, int departmentId) {
	        StringBuilder invalidFields = new StringBuilder();

	        if (subDepartmentDto.getSubDepartmentName() == null || subDepartmentDto.getSubDepartmentName().trim().isEmpty()) {
	            invalidFields.append("Sub-department name, ");
	        }
	        if (subDepartmentDto.getSubDepartmentDescription() == null || subDepartmentDto.getSubDepartmentDescription().trim().isEmpty()) {
	            invalidFields.append("Sub-department description, ");
	        }

	        if (invalidFields.length() > 0) {
	            invalidFields.setLength(invalidFields.length() - 2);
	            throw new InvalidInputException("The following fields cannot be null or blank: " + invalidFields.toString());
	        }

	        DepartmentEntity department = departmentRepository.findById(departmentId)
	                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

	        if (subDepartmentRepository.existsBySubDepartmentNameAndParentDepartmentDepartmentId(
	                subDepartmentDto.getSubDepartmentName(), departmentId)) {
	            throw new DuplicateDataException("Sub-department with name '" + subDepartmentDto.getSubDepartmentName()
	                    + "' already exists in Department ID: " + departmentId);
	        }

	        if (subDepartmentRepository.existsBySubDepartmentNameAndParentDepartmentDepartmentId(
	                subDepartmentDto.getSubDepartmentDescription(), departmentId)) {
	            throw new DuplicateDataException("Sub-department with description '" + subDepartmentDto.getSubDepartmentDescription()
	                    + "' already exists in Department ID: " + departmentId);
	        }

	        SubDepartmentEntity subDepartment = new SubDepartmentEntity();
	        subDepartment.setSubDepartmentName(subDepartmentDto.getSubDepartmentName());
	        subDepartment.setSubDepartmentDescription(subDepartmentDto.getSubDepartmentDescription());
	        subDepartment.setParentDepartment(department);

	        return subDepartmentRepository.save(subDepartment);
	    }

	
	    

	    
	    @Override
	    public String deleteSubDepartment(Long subDepartmentId) {
	        if (!subDepartmentRepository.existsById(subDepartmentId)) {
	            throw new SubDepartmentNotFoundException(subDepartmentId);
	        }
	        subDepartmentRepository.deleteById(subDepartmentId);
	        return "Sub-department deleted successfully with ID: " + subDepartmentId;
	    }


	    
	    @Override
	    public SubDepartmentEntity updateSubDepartment(Long subDepartmentId, SubDepartmentEntity subDepartmentEntity) {
	        SubDepartmentEntity existingSubDepartment = subDepartmentRepository.findById(subDepartmentId)
	                .orElseThrow(() -> new SubDepartmentNotFoundException(subDepartmentId));
	        
	        
	        
	        existingSubDepartment.setSubDepartmentName(subDepartmentEntity.getSubDepartmentName());
	        existingSubDepartment.setSubDepartmentDescription(subDepartmentEntity.getSubDepartmentDescription());
	        
	        return subDepartmentRepository.save(existingSubDepartment);
	    }
	


	    
	    @Override
	    public List<SubDepartmentEntity> getAllSubDepartments() {
	        return subDepartmentRepository.findAll();
	    }

	    @Override
	    public SubDepartmentEntity getSubDepartmentById(Long subDepartmentId) {
	        return subDepartmentRepository.findById(subDepartmentId)
	                .orElseThrow(() -> new SubDepartmentNotFoundException(subDepartmentId));
	    }
}
