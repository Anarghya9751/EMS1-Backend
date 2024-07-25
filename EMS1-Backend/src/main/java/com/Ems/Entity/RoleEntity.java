package com.EmployeeManagementSystem.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "Roles")
public class RoleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roleId;
	
	private String roleName;
	
	private String roleDescription;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "department_id")
	private DepartmentEntity department;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "sub_department_id")
	private SubDepartmentEntity subdepartment;
	
	
	@OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<EmployeeEntity> employees;
	    

	public Set<EmployeeEntity> getEmployees() {
			return employees;
		}

		public void setEmployees(Set<EmployeeEntity> employees) {
			this.employees = employees;
		}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public SubDepartmentEntity getSubdepartment() {
		return subdepartment;
	}

	public void setSubdepartment(SubDepartmentEntity subdepartment) {
		this.subdepartment = subdepartment;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	

}
