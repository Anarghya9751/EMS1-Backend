package com.ems.Service;

import java.util.List;

import com.ems.Entity.DepartmentEntity;
import com.ems.dto.DepartmentDto;

public interface DepartmentService {
	
	
	
	public DepartmentEntity AddDepartment(DepartmentEntity departmentEntity,Integer branchId,Long organizationId);
	
	
	public DepartmentDto  getDepartmentById(Long departmentId);
	
	
	List<DepartmentDto> getAllDepartments();
	
	
	public String deleteById(Long departmentId);
	
	
	public DepartmentEntity updateDepartment(DepartmentEntity departmentEntity,Long departmentId);

}
