package com.ems.ServiceImplementions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.Entity.DepartmentEntity;
import com.ems.Entity.SubDepartmentEntity;
import com.ems.Exception.DepartmentNotFoundException;
import com.ems.Exception.subDepartmentNotFoundException;
import com.ems.Repo.DepartmentRepository;
import com.ems.Repo.SubDepartmentRepository;
import com.ems.Service.SubDepartmentService;
import com.ems.dto.SubDepartmentDTO;

@Service
public class SubDepartmentServiceImpl implements SubDepartmentService {

	    @Autowired
	    private  SubDepartmentRepository subDepartmentRepository;
	    @Autowired
	    private  DepartmentRepository departmentRepository;

	    @Override
	    public SubDepartmentEntity saveSubDepartment(SubDepartmentDTO subDepartmentDto, Long departmentId) {
	        DepartmentEntity department = departmentRepository.findById(departmentId)
	                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));

	      SubDepartmentEntity subDepartment = new SubDepartmentEntity();
	        subDepartment.setSubDepartmentName(subDepartmentDto.getSubDepartmentName());
	        subDepartment.setSubDepartmentDescription(subDepartmentDto.getSubDepartmentDescription());
	        subDepartment.setDepartment(department);

	        return subDepartmentRepository.save(subDepartment);
	    }
	
	    @Override
	    public SubDepartmentEntity updateSubDepartment(Long subDepartmentId, SubDepartmentDTO subDepartmentDto) {
	        SubDepartmentEntity existingSubDepartment = subDepartmentRepository.findById(subDepartmentId)
	                .orElseThrow(() -> new subDepartmentNotFoundException(subDepartmentId));

	        existingSubDepartment.setSubDepartmentName(subDepartmentDto.getSubDepartmentName());
	        existingSubDepartment.setSubDepartmentDescription(subDepartmentDto.getSubDepartmentDescription());

	        return subDepartmentRepository.save(existingSubDepartment);
	    }

	    
	    @Override
	    public String deleteSubDepartment(Long subDepartmentId) {
	        if (!subDepartmentRepository.existsById(subDepartmentId)) {
	            throw new subDepartmentNotFoundException(subDepartmentId);
	        }
	        subDepartmentRepository.deleteById(subDepartmentId);
	        return "Sub-department deleted successfully with ID: " + subDepartmentId;
	    }

	    @Override
	    public List<SubDepartmentDTO> getAllSubDepartments() {
	        List<SubDepartmentEntity> subDepartments = subDepartmentRepository.findAll();
	        return subDepartments.stream()
	                .map(this::mapToDto)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public SubDepartmentDTO getSubDepartmentDtoById(Long subDepartmentId) {
	        SubDepartmentEntity subDepartmentEntity = subDepartmentRepository.findById(subDepartmentId)
	                .orElseThrow(() -> new subDepartmentNotFoundException(subDepartmentId));

	        return mapToDto(subDepartmentEntity);
	    }

	    private SubDepartmentDTO mapToDto(SubDepartmentEntity subDepartment) {
	        SubDepartmentDTO subDepartmentDto = new SubDepartmentDTO();
	        subDepartmentDto.setSubDepartmentId(subDepartment.getSubDepartmentId());
	        subDepartmentDto.setSubDepartmentName(subDepartment.getSubDepartmentName());
	        subDepartmentDto.setSubDepartmentDescription(subDepartment.getSubDepartmentDescription());
	        subDepartmentDto.setDepartmentId(subDepartment.getDepartment().getDepartmentId());
	        subDepartmentDto.setRoles(subDepartment.getRoles());
	        return subDepartmentDto;
	    }
}
