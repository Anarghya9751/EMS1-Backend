package com.EmployeeManagementSystem.ServiceImplementions;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.EmployeeManagementSystem.Entity.Entitylogin;
import com.EmployeeManagementSystem.Repository.Reprologin;
import com.EmployeeManagementSystem.Service.Servicelogin;
import com.EmployeeManagementSystem.dto.EntityloginDTO;

import jakarta.mail.MessagingException;

@Service
public class SevriceImpLogin implements Servicelogin {

    @Autowired
    private Reprologin reprolog;
    
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String saveUsernameAndPassword(EntityloginDTO entityDTO) {
        Entitylogin entity = new Entitylogin();
        entity.setUserName(entityDTO.getUserName());
        entity.setPassword(entityDTO.getPassword());
        entity.setRole(entityDTO.getRole());

        reprolog.save(entity);

        return "User saved successfully";
    }

    @Override
    public  ResponseEntity<Boolean> login(String username, String password) {
        Optional<Entitylogin> employee = reprolog.findByUserName(username);
        if (employee.isPresent()) {
            Entitylogin emp = employee.get();
            if (emp.getPassword().equals(password)) {
                boolean isSuperAdmin = "superadmin".equals(emp.getRole());
                return ResponseEntity.ok(isSuperAdmin); 
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false); 
    }

 
        

	@Override
	public Boolean updateUserPassword(String userName, String newPassword) {
	    Optional<Entitylogin> employee = reprolog.findByUserName(userName);
        if (employee.isPresent()) {
            Entitylogin emp = employee.get();
            emp.setPassword(newPassword); // Consider hashing the password
            reprolog.save(emp);
            return true; // Password updated successfully
        }
        return false; // Failed to update password
    }


	      

	@Override
	public Optional<Entitylogin> findByUsername(String userName) {
		return reprolog.findByUserName(userName);
	}

	@Override
	public void save(Entitylogin employee) {
		 reprolog.save(employee);
		
	}

	@Override
	public void sendPasswordReset(String string, String username, String newpassword) throws MessagingException {
		  SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(username);
	        message.setSubject("Password Reset Confirmation");
	        message.setText("Hello " + username + ",\n\nYour password has been reset successfully. " +
	                        "New password: " + newpassword + "\n\nBest Regards,\nYour Team");
	        javaMailSender.send(message); // Sending the email
	    }


		

		






}