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
import com.Ems.Service.EmployeeService;
import com.Ems.dto.EmployeeDTO;

@RestController
@CrossOrigin("***")
@RequestMapping("api/Employee")
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;

	 @PostMapping("/save/{branchId}/{departmentId}/{subDepartmentId}/{roleId}")
	    public ResponseEntity<String> saveEmployee(
	            @RequestParam("username") String username,
	            @RequestParam("password") String password,
	            @RequestParam("firstName") String firstName,
	            @RequestParam("lastName") String lastName,
	            @RequestParam("emailAddress") String emailAddress,
	            @RequestParam("contactNumber") String contactNumber,
	            @RequestParam("profileImages") MultipartFile profileImages,
	            @PathVariable("branchId") Integer branchId,
	            @PathVariable("departmentId") Integer departmentId,
	            @PathVariable("subDepartmentId") Long subDepartmentId,
	            @PathVariable("roleId") Long roleId)
	 {

	        EmployeeEntity employee = new EmployeeEntity();
	        employee.setUsername(username);
	        employee.setPassword(password);
	        employee.setFirstName(firstName);
	        employee.setLastName(lastName);
	        employee.setEmailAddress(emailAddress);
	        employee.setContactNumber(contactNumber);

	        try {
	            String filePath = saveFileToDisk(profileImages);
	            employee.setProfileImagePath(filePath);

	            String response = employeeService.saveEmployee(employee, branchId, departmentId, subDepartmentId, roleId, profileImages);
	            return ResponseEntity.ok(response);
	        } catch (IOException e) {
	            return ResponseEntity.status(500).body("Failed to save employee: " + e.getMessage());
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
	    
	    @GetMapping("/profile/{userId}")
	    public ResponseEntity<EmployeeDTO> viewProfile(@PathVariable Long userId)
	    {
	        EmployeeDTO employeeDTO = employeeService.findById(userId);
	        return employeeDTO != null ? ResponseEntity.ok(employeeDTO) : ResponseEntity.notFound().build();
	    }

	    @PutMapping("/profile/update/{userId}")
	    public ResponseEntity<String> updateProfile(
	            @PathVariable Long userId,
	            @RequestParam String username,
	            @RequestParam String firstName,
	            @RequestParam String lastName,
	            @RequestParam String emailAddress,
	            @RequestParam String contactNumber,
	            @RequestParam(required = false) Integer branchId,
	            @RequestParam(required = false) Integer departmentId,
	            @RequestParam(required = false) Long subDepartmentId,
	            @RequestParam(required = false) MultipartFile profileImage) {
	        try {
	            EmployeeDTO employeeDTO = new EmployeeDTO();
	            employeeDTO.setUserId(userId);
	            employeeDTO.setUsername(username);
	            employeeDTO.setFirstName(firstName);
	            employeeDTO.setLastName(lastName);
	            employeeDTO.setEmailAddress(emailAddress);
	            employeeDTO.setContactNumber(contactNumber);
	            employeeDTO.setBranchId(branchId);
	            employeeDTO.setDepartmentId(departmentId);
	            employeeDTO.setSubDepartmentId(subDepartmentId);

	            if (profileImage != null && !profileImage.isEmpty()) {
	                employeeDTO.setProfileImages(profileImage.getBytes());
	                employeeDTO.setProfileImagePath(profileImage.getOriginalFilename());
	            }

	            employeeService.updateProfile(employeeDTO);
	            return ResponseEntity.ok("Profile updated successfully");
	        } catch (EmployeeNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the profile");
	        }
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


	    
	    @GetMapping("/ListofallEmployees")
	    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
	        List<EmployeeDTO> employees = employeeService.getAllEmployees();
	        return ResponseEntity.ok(employees);
	    }
	    
	    @DeleteMapping("/Delete/{employeeId}")
	    public String deleteEmployee(@PathVariable("employeeId") Long employeeId) {
	        return employeeService.deleteEmployee(employeeId);
	    }
	   
}
