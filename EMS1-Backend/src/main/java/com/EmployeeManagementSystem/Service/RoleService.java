package com.EmployeeManagementSystem.Service;

import java.util.List;

import com.EmployeeManagementSystem.Entity.RoleEntity;
import com.EmployeeManagementSystem.dto.RoleDto;



public interface RoleService {
	
	public RoleEntity AddRole(RoleEntity roleEntity,Integer departrmentId,Long subDepartmentId);
	
	public RoleDto getRoleById(Long roleId);
	
	List<RoleEntity> getAllRole();
	
	public String deleteById(Long roleId);
	
	 RoleEntity updateRole(Long roleId,RoleEntity updateRole);
}
