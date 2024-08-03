package com.Ems.Controller;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ems.Entity.Entitylogin;
import com.Ems.Entity.OrganizationEntity;
import com.Ems.Repository.OrganizationRepository;
import com.Ems.Repository.Reprologin;
import com.Ems.Service.Servicelogin;
import com.Ems.dto.EntityloginDTO;
import com.Ems.sendEmail.SendEmail;

@RestController
@CrossOrigin("****")
@RequestMapping("api/User")
public class UserController {

	@Autowired
    private Servicelogin userService;
	
	@Autowired
	private Reprologin reprolog;
	
	@Autowired
	private OrganizationRepository orgRepo;
	
	@Autowired
	private SendEmail sendEmailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestParam String userName,
                                           @RequestParam String password,
                                           @RequestParam String role)
	{
        EntityloginDTO entityDTO = new EntityloginDTO();
        entityDTO.setUserName(userName);
        entityDTO.setPassword(password);
        entityDTO.setRole(role);

        String result = userService.saveUsernameAndPassword(entityDTO);
        return ResponseEntity.ok(result);
    }
	
	@PostMapping("/reset-password/{userName}")
	public ResponseEntity<String> otpForPasswordReset(@PathVariable String userName) {
	    try {
	        // First search in the Entitylogin repository
	        Optional<Entitylogin> empEnt = reprolog.findByUserName(userName);
	        if (empEnt.isPresent()) {
	            return handlePasswordReset(empEnt.get());
	        }

	        // If not found, search in the OrganizationEntity repository
	        Optional<OrganizationEntity> orgEnt = orgRepo.findByContactPersonEmail(userName);
	        if (orgEnt.isPresent()) {
	            // Assume that OrganizationEntity also has a method to get and set email OTP
	            OrganizationEntity org = orgEnt.get();
	            String otp = generateRandomOTP();
	            org.setEmailOtp(otp);

	            sendEmailForPasswordReset(org.getContactPersonEmail(), otp); // Use organization contact person email

	            orgRepo.save(org); // Save the organization with the new OTP

	            return ResponseEntity.ok("OTP sent to the registered email");
	        }

	        // If not found in both repositories
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending OTP");
	    }
	}

	private ResponseEntity<String> handlePasswordReset(Entitylogin emp) {
	    String otp = generateRandomOTP();
	    emp.setEmailOtp(otp);

	    sendEmailForPasswordReset(emp.getUserName(), otp); // Use user email

	    reprolog.save(emp); // Save the user with the new OTP

	    return ResponseEntity.ok("OTP sent to the registered email");
	}

	private String generateRandomOTP() {
	    Random random = new Random();
	    return String.format("%06d", random.nextInt(1000000)); // Ensure 6 digits
	}

	private void sendEmailForPasswordReset(String email, String otp) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(email);
	    message.setSubject("OTP Verification");
	    message.setText("Your OTP for password reset is: " + otp);
	    sendEmailService.send(message);
	}

	
	  @PostMapping("/reset-password/{userName}/{enteredOTP}/{newPassword}/{ConfirmPassword}")
	    public ResponseEntity<String> verifyOTPForNewPassword(
	            @PathVariable String userName,
	            @PathVariable String enteredOTP,
	            @PathVariable String newPassword,
	            @PathVariable String ConfirmPassword) {

	        if (!newPassword.equals(ConfirmPassword)) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match");
	        }

	        try {
	            Optional<Entitylogin> empEnt = reprolog.findByUserName(userName);
	            if (empEnt.isPresent()) {
	                Entitylogin user = empEnt.get();
	                if (user.getEmailOtp() != null && user.getEmailOtp().equals(enteredOTP)) {
	                    // Encrypt the new password
	                    user.setPassword(passwordEncoder.encode(newPassword));
	                    user.setEmailOtp(null); // Clear the OTP after successful verification
	                    userService.save(user); // Save the updated user
	                    return ResponseEntity.ok("Password reset successfully for Entitylogin user");
	                } else {
	                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP for Entitylogin user");
	                }
	            }

	            // Check in OrganizationEntity repository
	            Optional<OrganizationEntity> orgEnt = orgRepo.findByContactPersonEmail(userName);
	            if (orgEnt.isPresent()) {
	                OrganizationEntity orgUser = orgEnt.get();
	                if (orgUser.getEmailOtp() != null && orgUser.getEmailOtp().equals(enteredOTP)) {
	                    // Encrypt the new password
	                    orgUser.setPassword(passwordEncoder.encode(newPassword));
	                    orgUser.setEmailOtp(null); // Clear the OTP after successful verification
	                    orgRepo.save(orgUser); // Save the updated organization user
	                    return ResponseEntity.ok("Password reset successfully for OrganizationEntity user");
	                } else {
	                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP for OrganizationEntity user");
	                }
	            }

	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	        }
	    }
	
	
	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
	        return userService.login(username, password);
	    }

	    @PutMapping("/updatePassword")
	    public ResponseEntity<Boolean> updatePassword(@RequestParam String userName,
	                                                  @RequestParam String newPassword)
	    {
	        boolean isUpdated = userService.updateUserPassword(userName, newPassword);
	        return ResponseEntity.ok(isUpdated);
	    }

	    @PostMapping("/sendPasswordReset")
	    public ResponseEntity<Void> sendPasswordReset(@RequestParam String email,
	                                                  @RequestParam String userName,
	                                                  @RequestParam String newPassword)
	    {
	    	
	        try 
	        {
	        	
	        	userService.sendPasswordReset(email, userName, newPassword);
	            return ResponseEntity.ok().build();
	            
	            
	        }
	        catch (Exception e) 
	        {
	            return ResponseEntity.status(500).build();
	        }
	        
	        
	    }
	    
	    
	    
}
