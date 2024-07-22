package com.EmployeeManagementSystem.ServiceImplementions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagementSystem.Entity.DepartmentEntity;
import com.EmployeeManagementSystem.Entity.SubDepartmentEntity;
import com.EmployeeManagementSystem.Exception.DepartmentNotFoundException;
import com.EmployeeManagementSystem.Exception.SubDepartmentNotFoundException;
import com.EmployeeManagementSystem.Repository.BranchRepository;
import com.EmployeeManagementSystem.Repository.DepartmentRepository;
import com.EmployeeManagementSystem.Repository.SubDepartmentRepository;
import com.EmployeeManagementSystem.Service.SubDepartmentService;
import com.EmployeeManagementSystem.dto.DepartmentDto;
import com.EmployeeManagementSystem.dto.SubDepartmentDto;


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
	        DepartmentEntity department = departmentRepository.findById(departmentId)
	                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

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

//	    @Override
//	    public List<SubDepartmentDto> getAllSubDepartments() {
//	        List<SubDepartmentEntity> subDepartments = subDepartmentRepository.findAll();
//	        return subDepartments.stream()
//	                .map(this::mapToDto)
//	                .collect(Collectors.toList());
//	    }
//
//	    @Override
//	    public SubDepartmentDto getSubDepartmentDtoById(Long subDepartmentId) {
//	        SubDepartmentEntity subDepartmentEntity = subDepartmentRepository.findById(subDepartmentId)
//	                .orElseThrow(() -> new SubDepartmentNotFoundException(subDepartmentId));
//
//	        return mapToDto(subDepartmentEntity);
//	    }
//
//	    private SubDepartmentDto mapToDto(SubDepartmentEntity subDepartment) {
//	        SubDepartmentDto subDepartmentDto = new SubDepartmentDto();
//	        subDepartmentDto.setSubDepartmentId(subDepartment.getSubDepartmentId());
//	        subDepartmentDto.setSubDepartmentName(subDepartment.getSubDepartmentName());
//	        subDepartmentDto.setSubDepartmentDescription(subDepartment.getSubDepartmentDescription());
//	        subDepartmentDto.setDepartmentId(subDepartment.getParentDepartment().getDepartmentId());
//	        return subDepartmentDto;
//	    }
//	    
	    
	    @Override
	    public List<SubDepartmentDto> getAllSubDepartments() {
	        List<SubDepartmentEntity> subDepartments = subDepartmentRepository.findAll();
	        return subDepartments.stream()
	                .map(this::mapToDto)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public SubDepartmentDto getSubDepartmentDtoById(Long subDepartmentId) {
	        SubDepartmentEntity subDepartmentEntity = subDepartmentRepository.findById(subDepartmentId)
	                .orElseThrow(() -> new SubDepartmentNotFoundException(subDepartmentId));

	        return mapToDto(subDepartmentEntity);
	    }

	    private SubDepartmentDto mapToDto(SubDepartmentEntity subDepartment) {
	        SubDepartmentDto subDepartmentDto = new SubDepartmentDto();
	        subDepartmentDto.setSubDepartmentId(subDepartment.getSubDepartmentId());
	        subDepartmentDto.setSubDepartmentName(subDepartment.getSubDepartmentName());
	        subDepartmentDto.setSubDepartmentDescription(subDepartment.getSubDepartmentDescription());

	        // Setting department details
	        DepartmentDto departmentDto = new DepartmentDto();
	        DepartmentEntity parentDepartment = subDepartment.getParentDepartment();
	        departmentDto.setDepartmentId(parentDepartment.getDepartmentId());
	        departmentDto.setDepartmentname(parentDepartment.getDepartmentname());
	        departmentDto.setDepartmentDescription(parentDepartment.getDepartmentDescription());
	        departmentDto.setOrganizationId(parentDepartment.getOrganization().getOrganizationId());
	        departmentDto.setBranchId(parentDepartment.getBranch().getBranchId());

	        

	        subDepartmentDto.setDepartment(departmentDto);

	        return subDepartmentDto;
	    }
	    
	    
	    @Override
	    public SubDepartmentEntity updateSubDepartment(Long subDepartmentId, SubDepartmentEntity subDepartmentEntity) {
	        SubDepartmentEntity existingSubDepartment = subDepartmentRepository.findById(subDepartmentId)
	                .orElseThrow(() -> new SubDepartmentNotFoundException(subDepartmentId));
	        
	        
	        
	        existingSubDepartment.setSubDepartmentName(subDepartmentEntity.getSubDepartmentName());
	        existingSubDepartment.setSubDepartmentDescription(subDepartmentEntity.getSubDepartmentDescription());
	        
	        return subDepartmentRepository.save(existingSubDepartment);
	    }
	

}
