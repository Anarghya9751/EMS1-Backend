package com.Ems.Entity;

import javax.management.relation.Role;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "First name is mandatory")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "Email address is mandatory")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String emailAddress;

    @NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "\\+?[0-9. ()-]{7,25}", message = "Contact number is invalid")
    @Column(nullable = false, unique = true)
    private String contactNumber;

    @Lob
	@Column(columnDefinition = "LONGBLOB")
    private byte[] profileImages;
    
    private String profileImagePath;

    
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "branch_id", nullable = false)
    private BranchEntity branch;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sub_department_id", nullable = false)
    private SubDepartmentEntity subDepartment;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roles;

    


    

	public RoleEntity getRoles() {
		return roles;
	}

	public void setRoles(RoleEntity roles) {
		this.roles = roles;
	}

	public BranchEntity getBranch() {
		return branch;
	}

	public void setBranch(BranchEntity branch) {
		this.branch = branch;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

	
	public byte[] getProfileImages() {
		return profileImages;
	}

	public void setProfileImages(byte[] profileImages) {
		this.profileImages = profileImages;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	public SubDepartmentEntity getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(SubDepartmentEntity subDepartment) {
		this.subDepartment = subDepartment;
	}

	

    
}