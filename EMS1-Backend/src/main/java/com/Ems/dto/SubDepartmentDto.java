package com.Ems.dto;

import java.util.List;
import java.util.Set;

import com.Ems.Entity.DepartmentEntity;

public class SubDepartmentDto {

	
	private Long subDepartmentId;
    private String subDepartmentName;
    private String subDepartmentDescription;
    private Long organizationId;
    private int branchId;

    private int departmentId;
    
    private Set<DepartmentDto> departments; 
    

	
	
	
	
	public Set<DepartmentDto> getDepartments() {
		return departments;
	}
	public void setDepartments(Set<DepartmentDto> departments) {
		this.departments = departments;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
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
	
	
    
}
