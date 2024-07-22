package com.EmployeeManagementSystem.dto;

public class SubDepartmentDto {

	
	private Long subDepartmentId;
    private String subDepartmentName;
    private String subDepartmentDescription;
    private int departmentId;
    private DepartmentDto department;

    
    
	public DepartmentDto getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentDto department) {
		this.department = department;
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
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
    
}
