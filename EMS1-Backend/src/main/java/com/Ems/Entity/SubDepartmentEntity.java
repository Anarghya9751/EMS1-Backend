package com.Ems.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "sub_departments")
public class SubDepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subDepartmentId;

    private String subDepartmentName;

    private String subDepartmentDescription;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonManagedReference
    private DepartmentEntity parentDepartment;
    
    
   
    

	

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

	public DepartmentEntity getParentDepartment() {
		return parentDepartment;
	}

	public void setParentDepartment(DepartmentEntity parentDepartment) {
		this.parentDepartment = parentDepartment;
	}
    
    

}
