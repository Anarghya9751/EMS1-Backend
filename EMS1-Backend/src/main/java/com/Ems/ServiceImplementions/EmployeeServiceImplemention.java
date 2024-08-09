package com.Ems.ServiceImplementions;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Ems.Entity.BranchEntity;
import com.Ems.Entity.DepartmentEntity;
import com.Ems.Entity.EmployeeEntity;
import com.Ems.Entity.RoleEntity;
import com.Ems.Entity.SubDepartmentEntity;
import com.Ems.Exception.EmployeeNotFoundException;
import com.Ems.Exception.InvalidInputException;
import com.Ems.Exception.InvalidInputException;
import com.Ems.Repository.BranchRepository;
import com.Ems.Repository.DepartmentRepository;
import com.Ems.Repository.EmployeeRepository;
import com.Ems.Repository.RoleRepository;
import com.Ems.Repository.SubDepartmentRepository;
import com.Ems.Service.EmployeeService;
import com.Ems.dto.EmployeeDTO;

@Service
public class EmployeeServiceImplemention implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private SubDepartmentRepository subDepartmentRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
    private RoleRepository roleRepository;

	@Override
	public String saveEmployee(EmployeeEntity employee, Long roleId, MultipartFile profileImages) throws IOException {
	    StringBuilder invalidFields = new StringBuilder();

	    // Validate required fields
	    if (employee.getUsername() == null || employee.getUsername().trim().isEmpty()) {
	        invalidFields.append("Username, ");
	    }
	    if (employee.getEmailAddress() == null || employee.getEmailAddress().trim().isEmpty()) {
	        invalidFields.append("Email address, ");
	    }
	    if (employee.getContactNumber() == null || employee.getContactNumber().trim().isEmpty()) {
	        invalidFields.append("Contact number, ");
	    }
	    if (employee.getFirstName() == null || employee.getFirstName().trim().isEmpty()) {
	        invalidFields.append("First name, ");
	    }
	    if (employee.getLastName() == null || employee.getLastName().trim().isEmpty()) {
	        invalidFields.append("Last name, ");
	    }

	    if (invalidFields.length() > 0) {
	        invalidFields.setLength(invalidFields.length() - 2); // Remove trailing comma and space
	        throw new InvalidInputException("The following fields cannot be null or blank: " + invalidFields.toString());
	    }

	    // Handle profile image if provided
	    if (profileImages != null && !profileImages.isEmpty()) {
	        employee.setProfileImages(profileImages.getBytes());
	    }

	    // Validate and set role
	    Optional<RoleEntity> role = roleRepository.findById(roleId);
	    if (role.isPresent()) {
	        employee.setRoles(role.get());
	    } else {
	        throw new InvalidInputException("Role not found");
	    }

	    // Check for duplicate entries
	    if (employeeRepository.existsByUsername(employee.getUsername())) {
	        return "Username already exists";
	    }
	    if (employeeRepository.existsByEmailAddress(employee.getEmailAddress())) {
	        return "Email address already exists";
	    }
	    if (employeeRepository.existsByContactNumber(employee.getContactNumber())) {
	        return "Contact number already exists";
	    }
	    if (employeeRepository.existsByFirstName(employee.getFirstName())) {
	        return "Firstname already exists";
	    }
	    if (employeeRepository.existsByLastName(employee.getLastName())) {
	        return "Lastname already exists";
	    }

	    // Save employee
	    employeeRepository.save(employee);
	    return "Employee saved successfully";
	}

	
	

	@Override
	public void changePassword(Long userId, String password) {
		EmployeeEntity employee = employeeRepository.findById(userId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with. id: " + userId));

		String encodedPassword = passwordEncoder.encode(password);
		employee.setPassword(encodedPassword);

		employeeRepository.save(employee);
	}

	@Override
	public void updateProfileImage(Long userId, MultipartFile profileImages) throws IOException {
		EmployeeEntity employee = employeeRepository.findById(userId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + userId));

		if (profileImages != null && !profileImages.isEmpty()) {
			employee.setProfileImages(profileImages.getBytes());
			employee.setProfileImagePath(profileImages.getOriginalFilename());
		}

		employeeRepository.save(employee);
	}

	private EmployeeEntity convertToEntity(EmployeeDTO dto) {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setUserId(dto.getUserId());
		employee.setUsername(dto.getUsername());
		employee.setPassword(dto.getPassword());
		employee.setFirstName(dto.getFirstName());
		employee.setLastName(dto.getLastName());
		employee.setEmailAddress(dto.getEmailAddress());
		employee.setContactNumber(dto.getContactNumber());
		employee.setProfileImagePath(dto.getProfileImagePath());
		return employee;
	}

	



	@Override
	public String deleteEmployee(Long employeeId) {
		if (employeeRepository.existsById(employeeId)) {
			employeeRepository.deleteById(employeeId);
			return "Employee deleted successfully";
		} else {
			return "Employee not found with id: " + employeeId;
		}
	}

	 public EmployeeEntity updateEmployee(Long userId, EmployeeEntity employeeUpdates) {
	        EmployeeEntity employee = employeeRepository.findById(userId)
	                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + userId));

	        // Update fields
	        employee.setUsername(employeeUpdates.getUsername());
	        employee.setPassword(employeeUpdates.getPassword());
	        employee.setFirstName(employeeUpdates.getFirstName());
	        employee.setLastName(employeeUpdates.getLastName());
	        employee.setEmailAddress(employeeUpdates.getEmailAddress());
	        employee.setContactNumber(employeeUpdates.getContactNumber());
	        employee.setProfileImagePath(employeeUpdates.getProfileImagePath());
	        employee.setProfileImages(employeeUpdates.getProfileImages());
	        employee.setRoles(employeeUpdates.getRoles());

	        return employeeRepository.save(employee);
	    }

	    // Get Employee by ID
	    public EmployeeEntity getEmployeeById(Long userId) {
	        return employeeRepository.findById(userId)
	                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + userId));
	    }

	    // Get All Employees
	    public List<EmployeeEntity> getAllEmployees() {
	        return employeeRepository.findAll();
	    }
	
}
