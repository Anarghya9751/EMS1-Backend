package com.ems.Controller;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.Entity.Entitylogin;
import com.ems.Service.Servicelogin;
import com.ems.dto.EntityloginDTO;
import com.ems.sendEmail.SendEmail;

    @CrossOrigin(origins = "*")
    @RestController
    @RequestMapping("/api")
    public class ControllerLogin {

        @Autowired
        private Servicelogin service;

        @Autowired
        private SendEmail sendEmailService;

        @PostMapping("/save")
        public ResponseEntity<String> saveUsernameAndPassword(@RequestBody EntityloginDTO entityDTO) {
            Boolean status = service.saveUsernameAndPassword(entityDTO);
            if (status) {
                return new ResponseEntity<>("Data Saved Successfully", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Data Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody EntityloginDTO userDTO) {
            ResponseEntity<Boolean> response = service.login(userDTO.getUserName(), userDTO.getPassword());
            
            if (response.getStatusCode() == HttpStatus.OK) {
                boolean isSuperAdmin = response.getBody();
                return ResponseEntity.ok(isSuperAdmin ? "Superadmin login successful" : "Admin login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }

        @PostMapping("/reset-password/{userName}")
        public ResponseEntity<String> otpForPasswordReset(@PathVariable String userName) {
            try {
                Optional<Entitylogin> empEnt = service.findByUsername(userName);
                if (empEnt.isPresent()) {
                    Entitylogin emp = empEnt.get();

                    String otp = generateRandomOTP();
                    emp.setEmailOtp(otp);

                    sendEmailForPasswordReset(emp.getUserName(), otp); // Use user email

                    service.save(emp); // Save the user with the new OTP

                    return ResponseEntity.ok("OTP sent to the registered email");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending OTP");
            }
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

            try {
                Optional<Entitylogin> empEnt = service.findByUsername(userName);
                if (empEnt.isPresent()) {
                    Entitylogin user = empEnt.get();
                    System.out.println("Generated OTP: " + user.getEmailOtp());
                    System.out.println("Entered OTP: " + enteredOTP);

                    if (user.getEmailOtp() != null && user.getEmailOtp().equals(enteredOTP)) {
                        // Hash the password in production
                        user.setPassword(newPassword);
                        user.setEmailOtp(null); // Clear the OTP after successful verification
                        service.save(user); // Save the updated user
                        return ResponseEntity.ok("Password reset successfully");
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
            }
        }
    

}
