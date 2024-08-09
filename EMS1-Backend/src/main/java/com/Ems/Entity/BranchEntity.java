package com.Ems.Entity;

import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Branch")
public class BranchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer branchId;

    @NotBlank(message = "Branch name is required")
    @Size(min = 3, max = 20, message = "Branch name must be between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)?$", message = "Branch name should contain only alphabets with an optional single space")
    private String branchName;

    @NotBlank(message = "Branch description is required")
    private String branchDescription;

    @NotBlank(message = "Branch address is required")
    private String branchAddress;

    @NotNull(message = "Contact number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Contact number must start with a digit between 6 and 9 and be exactly 10 digits")
    private String branchContactNumber;

	

    
    
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;
    
    

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DepartmentEntity> departments;

    
   
   
    
   
	public BranchEntity() {
        super();
    }

    public BranchEntity(Integer branchId, String branchName, String branchDescription, String branchAddress, 
                        String branchContactNumber) {
        super();
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchDescription = branchDescription;
        this.branchAddress = branchAddress;
        this.branchContactNumber = branchContactNumber;
       
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchDescription() {
        return branchDescription;
    }

    public void setBranchDescription(String branchDescription) {
        this.branchDescription = branchDescription;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchContactNumber() {
        return branchContactNumber;
    }

    public void setBranchContactNumber(String branchContactNumber) {
        this.branchContactNumber = branchContactNumber;
    }

    
	/*
	 * public LocalDate getCreatedDate() { return createdDate; }
	 * 
	 * public void setCreatedDate(LocalDate createdDate) { this.createdDate =
	 * createdDate; }
	 * 
	 * public LocalTime getCreatedTime() { return createdTime; }
	 * 
	 * public void setCreatedTime(LocalTime createdTime) { this.createdTime =
	 * createdTime; }
	 */
   

    public OrganizationEntity getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationEntity organization) {
		this.organization = organization;
	}
	

	

	

	public Set<DepartmentEntity> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<DepartmentEntity> departments) {
		this.departments = departments;
	}

	@Override
    public String toString() {
        return "BranchEntity [branchId=" + branchId + ", branchName=" + branchName + ", branchDescription=" + branchDescription
                + ", branchAddress=" + branchAddress + ", branchContactNumber=" + branchContactNumber
                 + "]";
    }
}