package com.EmployeeManagementSystem.dto;

import java.util.List;
import java.util.Set;


public class DepartmentDto {

    private int departmentId;
    private String Departmentname;
    private String DepartmentDescription;

    private Long organizationId;
    private int branchId;
    
    private List<SubDepartmentDto> subDepartments; 
    
    private List<Long> subDepartmentId; 


	
	
	
	
	
	public List<Long> getSubDepartmentId() {
		return subDepartmentId;
	}
	public void setSubDepartmentId(List<Long> subDepartmentId) {
		this.subDepartmentId = subDepartmentId;
	}
	public List<SubDepartmentDto> getSubDepartments() {
		return subDepartments;
	}
	public void setSubDepartments(List<SubDepartmentDto> subDepartments) {
		this.subDepartments = subDepartments;
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
