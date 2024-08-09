package com.Ems.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Ems.Entity.EmployeeEntity;
import com.Ems.Exception.EmployeeNotFoundException;
import com.Ems.Exception.InvalidInputException;
import com.Ems.Service.EmployeeService;
import com.Ems.dto.EmployeeDTO;

@RestController
@CrossOrigin("***")
@RequestMapping("api/Employee")
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

//	 @PostMapping("/save/{branchId}/{departmentId}/{subDepartmentId}/{roleId}")
//	    public ResponseEntity<String> saveEmployee(
//	            @RequestParam("username") String username,
//	            @RequestParam("password") String password,
//	            @RequestParam("firstName") String firstName,
//	            @RequestParam("lastName") String lastName,
//	            @RequestParam("emailAddress") String emailAddress,
//	            @RequestParam("contactNumber") String contactNumber,
//	            @RequestParam("profileImages") MultipartFile profileImages,
//	            @PathVariable("branchId") Integer branchId,
//	            @PathVariable("departmentId") Integer departmentId,
//	            @PathVariable("subDepartmentId") Long subDepartmentId,
//	            @PathVariable("roleId") Long roleId)
//	 {
//
//	        EmployeeEntity employee = new EmployeeEntity();
//	        employee.setUsername(username);
//	        employee.setPassword(password);
//	        employee.setFirstName(firstName);
//	        employee.setLastName(lastName);
//	        employee.setEmailAddress(emailAddress);
//	        employee.setContactNumber(contactNumber);
//
//	        try {
//	            String filePath = saveFileToDisk(profileImages);
//	            employee.setProfileImagePath(filePath);
//
//	            String response = employeeService.saveEmployee(employee, branchId, departmentId, subDepartmentId, roleId, profileImages);
//	            return ResponseEntity.ok(response);
//	        } catch (IOException e) {
//	            return ResponseEntity.status(500).body("Failed to save employee: " + e.getMessage());
//	        }
//	    }
//
//	    private String saveFileToDisk(MultipartFile file) throws IOException {
//	        String directory = "C:\\Users\\KC\\Documents\\postman";
//	        File dir = new File(directory);
//	        if (!dir.exists()) {
//	            dir.mkdirs();
//	        }
//
//	        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//	        File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
//
//	        try (FileOutputStream fos = new FileOutputStream(serverFile)) {
//	            fos.write(file.getBytes());
//	        }
//
//	        return serverFile.getAbsolutePath();
//	    }
	
	@PostMapping("/save/{roleId}")
	public ResponseEntity<String> saveEmployee(
	        @RequestParam("username") String username,
	        @RequestParam("password") String password,
	        @RequestParam("firstName") String firstName,
	        @RequestParam("lastName") String lastName,
	        @RequestParam("emailAddress") String emailAddress,
	        @RequestParam("contactNumber") String contactNumber,
	        @RequestParam(value = "profileImages", required = false) MultipartFile profileImages,
	        @PathVariable("roleId") Long roleId) {

	    EmployeeEntity employee = new EmployeeEntity();
	    employee.setUsername(username);
	    employee.setPassword(password);
	    employee.setFirstName(firstName);
	    employee.setLastName(lastName);
	    employee.setEmailAddress(emailAddress);
	    employee.setContactNumber(contactNumber);

	    try {
	        if (profileImages != null && !profileImages.isEmpty()) {
	            // Save the file and get the file path
	            String filePath = saveFileToDisk(profileImages);
	            employee.setProfileImagePath(filePath);
	        }

	        // Call the service method to save the employee
	        String response = employeeService.saveEmployee(employee, roleId, profileImages);
	        return ResponseEntity.ok(response);

	    } catch (InvalidInputException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to save employee: " + e.getMessage());
	    }
	}

	private String saveFileToDisk(MultipartFile file) throws IOException {
	    String directory = "C:\\Users\\KC\\Documents\\postman";
	    File dir = new File(directory);
	    if (!dir.exists()) {
	        dir.mkdirs();
	    }

	    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	    File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);

	    try (FileOutputStream fos = new FileOutputStream(serverFile)) {
	        fos.write(file.getBytes());
	    }

	    return serverFile.getAbsolutePath();
	}

	    
	   

	    


	   
 
	    @PutMapping("/profile/update-ProfileImage/{userId}")
	    public ResponseEntity<String> updateProfileImage(
	            @PathVariable Long userId,
	            @RequestParam MultipartFile profileImages) {
	        try {
	            employeeService.updateProfileImage(userId, profileImages);
	            return ResponseEntity.ok("UserID"+userId+" is Profile image updated successfully ");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the profile image");
	        }
	    }
	    
	    @PutMapping("/profile/changePassword/{userId}")
	    public ResponseEntity<String> changePassword(
	            @PathVariable Long userId,
	            @RequestParam String password) {
	        try {
	            employeeService.changePassword(userId, password);
	            return ResponseEntity.ok("Password changed successfully for user ID: " + userId);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while changing the password for user ID: " + userId);
	        }
	    }


	    
	   
	    
	    @DeleteMapping("/Delete/{employeeId}")
	    public String deleteEmployee(@PathVariable("employeeId") Long employeeId) {
	        return employeeService.deleteEmployee(employeeId);
	    }
	    
	    
	    @PutMapping("/update/{userId}")
	    public ResponseEntity<EmployeeEntity> updateEmployee(
	            @PathVariable Long userId,
	            @RequestBody EmployeeEntity employeeUpdates) {
	        EmployeeEntity updatedEmployee = employeeService.updateEmployee(userId, employeeUpdates);
	        return ResponseEntity.ok(updatedEmployee);
	    }

	    @GetMapping("/getbyid/{userId}")
	    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long userId) {
	        EmployeeEntity employee = employeeService.getEmployeeById(userId);
	        return ResponseEntity.ok(employee);
	    }

	    @GetMapping("/ListofallEmployees")
	    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
	        List<EmployeeEntity> employees = employeeService.getAllEmployees();
	        return ResponseEntity.ok(employees);
	    }
	   
}
