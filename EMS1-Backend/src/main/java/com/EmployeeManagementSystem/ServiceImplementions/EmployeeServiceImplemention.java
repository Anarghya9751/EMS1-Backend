package com.EmployeeManagementSystem.ServiceImplementions;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.EmployeeManagementSystem.Entity.BranchEntity;
import com.EmployeeManagementSystem.Entity.DepartmentEntity;
import com.EmployeeManagementSystem.Entity.EmployeeEntity;
import com.EmployeeManagementSystem.Entity.SubDepartmentEntity;
import com.EmployeeManagementSystem.Exception.EmployeeNotFoundException;
import com.EmployeeManagementSystem.Repository.BranchRepository;
import com.EmployeeManagementSystem.Repository.DepartmentRepository;
import com.EmployeeManagementSystem.Repository.EmployeeRepository;
import com.EmployeeManagementSystem.Repository.SubDepartmentRepository;
import com.EmployeeManagementSystem.Service.EmployeeService;
import com.EmployeeManagementSystem.dto.EmployeeDTO;

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

	public String saveEmployee(EmployeeEntity employee, Integer branchId, Integer departmentId, Long subDepartmentId,
			MultipartFile profileImages) throws IOException {
		if (profileImages != null && !profileImages.isEmpty()) {
			employee.setProfileImages(profileImages.getBytes());
		}

		Optional<BranchEntity> branch = branchRepository.findById(branchId);
		if (branch.isPresent()) {
			employee.setBranch(branch.get());
		} else {
			return "Branch not found";
		}

		Optional<DepartmentEntity> department = departmentRepository.findById(departmentId);
		if (department.isPresent()) {
			employee.setDepartment(department.get());
		} else {
			return "Department not found";
		}

		Optional<SubDepartmentEntity> subDepartment = subDepartmentRepository.findById(subDepartmentId);
		if (subDepartment.isPresent()) {
			employee.setSubDepartment(subDepartment.get());
		} else {
			return "Sub-department not found";
		}
		employeeRepository.save(employee);

		return "Employee saved successfully";
	}

	@Override
	public EmployeeDTO findById(Long userId) {
		EmployeeEntity employee = employeeRepository.findById(userId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + userId));
		return convertToDTO(employee);
	}

	@Override
	public void saveEmployee(EmployeeDTO employeeDTO) {
		EmployeeEntity employee = convertToEntity(employeeDTO);
		employeeRepository.save(employee);
	}

	@Override
	public void updateProfile(EmployeeDTO employeeDTO) {
		EmployeeEntity employee = employeeRepository.findById(employeeDTO.getUserId()).orElseThrow(
				() -> new EmployeeNotFoundException("Employee not found with id: " + employeeDTO.getUserId()));

		employee.setUsername(employeeDTO.getUsername());
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		employee.setEmailAddress(employeeDTO.getEmailAddress());
		employee.setContactNumber(employeeDTO.getContactNumber());
		employee.setProfileImagePath(employeeDTO.getProfileImagePath());
		employee.setProfileImages(employeeDTO.getProfileImages());

		Optional.ofNullable(employeeDTO.getBranchId()).flatMap(branchRepository::findById)
				.ifPresent(employee::setBranch);

		Optional.ofNullable(employeeDTO.getDepartmentId()).flatMap(departmentRepository::findById)
				.ifPresent(employee::setDepartment);

		Optional.ofNullable(employeeDTO.getSubDepartmentId()).flatMap(subDepartmentRepository::findById)
				.ifPresent(employee::setSubDepartment);

		employeeRepository.save(employee);
	}

	@Override
	public void changePassword(Long userId, String password) {
		EmployeeEntity employee = employeeRepository.findById(userId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + userId));

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
	public List<EmployeeDTO> getAllEmployees() {
		List<EmployeeEntity> employees = employeeRepository.findAll();
		return employees.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private EmployeeDTO convertToDTO(EmployeeEntity employee) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setUserId(employee.getUserId());
		dto.setUsername(employee.getUsername());
		dto.setPassword(employee.getPassword()); // Consider not including password in DTO
		dto.setFirstName(employee.getFirstName());
		dto.setLastName(employee.getLastName());
		dto.setEmailAddress(employee.getEmailAddress());
		dto.setContactNumber(employee.getContactNumber());
		dto.setProfileImages(employee.getProfileImages());
		dto.setBranchId(employee.getBranch() != null ? employee.getBranch().getBranchId() : 0);
		dto.setDepartmentId(employee.getDepartment() != null ? employee.getDepartment().getDepartmentId() : 0);
		dto.setSubDepartmentId(
				employee.getSubDepartment() != null ? employee.getSubDepartment().getSubDepartmentId() : 0);
		dto.setProfileImagePath(employee.getProfileImagePath()); // Ensure this is set properly
		return dto;
	}

//        private EmployeeDTO convertToDTO(EmployeeEntity employee) {
//            EmployeeDTO dto = new EmployeeDTO();
//            dto.setUserId(employee.getUserId());
//            dto.setUsername(employee.getUsername());
//            dto.setPassword(employee.getPassword());
//            dto.setFirstName(employee.getFirstName());
//            dto.setLastName(employee.getLastName());
//            dto.setEmailAddress(employee.getEmailAddress());
//            dto.setContactNumber(employee.getContactNumber());
//            dto.setProfileImagePath(employee.getProfileImagePath());
//            dto.setProfileImages(employee.getProfileImages());
//            dto.setBranchId(employee.getBranch().getBranchId());
//            dto.setDepartmentId(employee.getDepartment().getDepartmentId());
//            dto.setSubDepartmentId(employee.getSubDepartment().getSubDepartmentId());
//
//
//    

	@Override
	public String deleteEmployee(Long employeeId) {
		if (employeeRepository.existsById(employeeId)) {
			employeeRepository.deleteById(employeeId);
			return "Employee deleted successfully";
		} else {
			return "Employee not found with id: " + employeeId;
		}
	}

}
