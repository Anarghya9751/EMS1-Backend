package com.Ems.Service;

import java.util.List;

import com.Ems.Entity.RoleEntity;
import com.Ems.dto.RoleDto;



public interface RoleService {
	
	public RoleEntity AddRole(RoleEntity roleEntity,Integer departrmentId,Long subDepartmentId);
	
	public RoleDto getRoleById(Long roleId);
	
	List<RoleEntity> getAllRole();
	
	public String deleteById(Long roleId);
	
	 RoleEntity updateRole(Long roleId,RoleEntity updateRole);
}
