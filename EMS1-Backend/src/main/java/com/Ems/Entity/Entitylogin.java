package com.Ems.Entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "login")
public class Entitylogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logId;

    private String userName;
    private String password;
    private String emailOtp;
    private String role;

    public Entitylogin(long logId, String userName, String password, String emailOtp, String role) {
		super();
		this.logId = logId;
		this.userName = userName;
		this.password = password;
		this.emailOtp = emailOtp;
		this.role =role;
	}

    @OneToMany(mappedBy = "login",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<OrganizationEntity> organizations;
    
    
    

	public Set<OrganizationEntity> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<OrganizationEntity> organizations) {
		this.organizations = organizations;
	}

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getEmailOtp() {
        return emailOtp; 
    }

    public void setEmailOtp(String emailOtp) {
        this.emailOtp = emailOtp;
    }

    public Entitylogin() {
        super();
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
