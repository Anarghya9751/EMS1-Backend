package com.Ems.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "organizations")
public class OrganizationEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizationId;
    
    @NotBlank(message = "Organization Name cannot be blank")
    private String organizationName;
    
    @NotBlank(message = "Organization Type cannot be blank")
    private String organizationType;
    
    @NotBlank(message = "Location cannot be blank")
    private String location;
    
    @NotBlank(message = "Contact Person Name cannot be blank")
    private String contactPersonName;
    
    private String password;
    
    @NotBlank(message = "Role cannot be blank")
    private String role;
    
    private String emailOtp;

    @NotBlank(message = "Email address cannot be blank")
    @Email(message = "Invalid email format")
    private String contactPersonEmail;

    @NotNull(message = "Contact number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Contact number must start with a digit between 6 and 9 and be exactly 10 digits")
    private String contactPersonPhoneNumber;

    @NotBlank(message = "Website URL cannot be blank")
    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format")
    private String WebsiteURL;

    @NotBlank(message = "Logo URL cannot be blank")
    private String logoURL;

    @NotBlank(message = "Registration Number cannot be blank")
    private String registrationNumber;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<BranchEntity> branches;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<DepartmentEntity> departments;

    // Getters and Setters

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

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

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

    public Set<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentEntity> departments) {
        this.departments = departments;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmailOtp() {
        return emailOtp;
    }

    public void setEmailOtp(String emailOtp) {
        this.emailOtp = emailOtp;
    }
}
