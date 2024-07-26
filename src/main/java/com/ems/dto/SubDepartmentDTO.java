package com.ems.dto;

import java.util.List;

import com.ems.Entity.RoleEntity;

public class SubDepartmentDTO {
	
	private Long subDepartmentId;

    private String subDepartmentName;

    private String subDepartmentDescription;
    
    private Long departmentId;
    
  
    private List<RoleEntity> roles;

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public Long getSubDepartmentId() {
		return subDepartmentId;
	}

	public void setSubDepartmentId(Long subDepartmentId) {
		this.subDepartmentId = subDepartmentId;
	}

	public String getSubDepartmentName() {
		return subDepartmentName;
	}

	public void setSubDepartmentName(String subDepartmentName) {
		this.subDepartmentName = subDepartmentName;
	}

	public String getSubDepartmentDescription() {
		return subDepartmentDescription;
	}

	public void setSubDepartmentDescription(String subDepartmentDescription) {
		this.subDepartmentDescription = subDepartmentDescription;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	
	

	

    
    

}
