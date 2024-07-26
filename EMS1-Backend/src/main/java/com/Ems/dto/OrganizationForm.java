package com.Ems.dto;

import java.util.Set;

import com.Ems.Entity.BranchEntity;


public class OrganizationForm {
	private Long organizationId;
	private String organizationName;
	private String organizationType;
	private String location;
	private String contactPersonName;
	private String contactPersonEmail;
	private String contactPersonPhoneNumber;
	private String WebsiteURL;
	private String logoURL;
//	private byte[] logo;
//	private String documentPath;
	  
    private String registrationNumber;
	private String description;
	private Set<BranchEntity> branches;
	public String getLogoURL() {
		return logoURL;
	}
	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContactPersonName() {
		return contactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}
	public String getContactPersonEmail() {
		return contactPersonEmail;
	}
	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}
	public String getContactPersonPhoneNumber() {
		return contactPersonPhoneNumber;
	}
	public void setContactPersonPhoneNumber(String contactPersonPhoneNumber) {
		this.contactPersonPhoneNumber = contactPersonPhoneNumber;
	}
	public String getWebsiteURL() {
		return WebsiteURL;
	}
	public void setWebsiteURL(String websiteURL) {
		WebsiteURL = websiteURL;
	}
//	public byte[] getLogo() {
//		return logo;
//	}
//	public void setLogo(byte[] logo) {
//		this.logo = logo;
//	}
//	public String getDocumentPath() {
//		return documentPath;
//	}
//	public void setDocumentPath(String documentPath) {
//		this.documentPath = documentPath;
//	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<BranchEntity> getBranches() {
		return branches;
	}
	public void setBranches(Set<BranchEntity> branches) {
		this.branches = branches;
	}

}
