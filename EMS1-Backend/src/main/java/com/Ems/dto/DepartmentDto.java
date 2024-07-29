package com.Ems.dto;

import java.util.List;
import java.util.Set;

import com.Ems.Entity.SubDepartmentEntity;


public class DepartmentDto {

    private int departmentId;
    private String Departmentname;
    private String DepartmentDescription;

    private Long organizationId;
    private int branchId;

	private Set<SubDepartmentEntity> subdepartments;



	
	
	
	
	
	
	
	
	public Set<SubDepartmentEntity> getSubdepartments() {
		return subdepartments;
	}
	public void setSubdepartments(Set<SubDepartmentEntity> subdepartments) {
		this.subdepartments = subdepartments;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentname() {
		return Departmentname;
	}
	public void setDepartmentname(String departmentname) {
		Departmentname = departmentname;
	}
	public String getDepartmentDescription() {
		return DepartmentDescription;
	}
	public void setDepartmentDescription(String departmentDescription) {
		DepartmentDescription = departmentDescription;
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
	
    


}
