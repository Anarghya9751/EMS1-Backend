package com.ems.Service;

import java.util.List;

import com.ems.Entity.RoleEntity;
import com.ems.dto.RoleDto;

public interface RoleService {
	
	public RoleEntity AddRole(RoleEntity roleEntity,Long departrmentId,Long subDepartmentId);
	
	public RoleDto getRoleById(Long roleId);
	
	List<RoleEntity> getAllRole();
	
	public String deleteById(Long roleId);
	
	 RoleEntity updateRole(Long roleId,RoleEntity updateRole);
}
