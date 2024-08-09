package com.Ems.Entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;

    @Column
    @NotBlank(message = "Department name is mandatory")
    private String Departmentname;

    @Column
    @NotBlank(message = "Department DepartmentDescription is mandatory")
    private String DepartmentDescription;

  
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "branchId")
    private BranchEntity branch;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "organizationId")
    private OrganizationEntity organization;
    
    
    @OneToMany(mappedBy = "parentDepartment", cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<SubDepartmentEntity> subDepartments;
    
   
    

	

	

	

	
	public Set<SubDepartmentEntity> getSubDepartments() {
		return subDepartments;
	}

	public void setSubDepartments(Set<SubDepartmentEntity> subDepartments) {
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
    
    

    

}
    
    
    