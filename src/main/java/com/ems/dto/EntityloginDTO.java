package com.ems.dto;

public class EntityloginDTO {

    private String userName;
    private String password;
    private String emailOtp;
    private String role;
    
    
	public EntityloginDTO() {
	}


	public EntityloginDTO(String userName, String newPassword, String password, String emailOtp, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.emailOtp = emailOtp;
		this.role = role;
	}


	public String getEmailOtp() {
		return emailOtp;
	}


	public void setEmailOtp(String emailOtp) {
		this.emailOtp = emailOtp;
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


	public void setPassword(String password) {
		this.password = password;
	}


	public String getOtp() {
		return emailOtp;
	}


	public void setOtp(String otp) {
		emailOtp = otp;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
  
	
}
