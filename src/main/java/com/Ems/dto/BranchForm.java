package com.Ems.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public class BranchForm {

    private String branchName;
    private String branchDescription;
    private String branchAddress;
    private String branchContactNumber;
    private Long organizationId;
	
	private int branchId;
   
    

	

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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
}
