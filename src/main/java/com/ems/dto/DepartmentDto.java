package com.ems.dto;

import java.util.List;

import com.ems.Entity.SubDepartmentEntity;

public class DepartmentDto {

	
    private Long departmentId;
	
	
	private String departmentName;
	
	
	private String departmentDescription;
	
	
	private Long organizationId;
	
	private int branchId;
	
	private List<SubDepartmentEntity> subDepartments;
	
	




	public List<SubDepartmentEntity> getSubDepartments() {
		return subDepartments;
	}


	public void setSubDepartments(List<SubDepartmentEntity> subDepartments) {
		this.subDepartments = subDepartments;
	}


	public Long getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}


	public int getBranchId() {
		return branchId;
	}


	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}


	public Long getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public String getDepartmentDescription() {
		return departmentDescription;
	}


	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}
	
	
	
	
}
