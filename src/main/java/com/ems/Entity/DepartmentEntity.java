package com.ems.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class DepartmentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long departmentId;
	
	
	private String departmentName;
	
	
	private String departmentDescription;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "branch_id")
	private BranchEntity branch;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "organization_id")
	private OrganizationEntity organization;
	
	@OneToMany(mappedBy = "Department",cascade =  CascadeType.ALL )
	@JsonIgnore
	private List<SubDepartmentEntity> subDepartments;

	

	public List<SubDepartmentEntity> getSubDepartments() {
		return subDepartments;
	}


	public void setSubDepartments(List<SubDepartmentEntity> subDepartments) {
		this.subDepartments = subDepartments;
	}


	public BranchEntity getBranch() {
		return branch;
	}


	public void setBranch(BranchEntity branch) {
		this.branch = branch;
	}


	public OrganizationEntity getOrganization() {
		return organization;
	}


	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
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
