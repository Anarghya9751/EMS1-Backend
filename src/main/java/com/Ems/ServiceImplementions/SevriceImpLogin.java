package com.Ems.ServiceImplementions;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Ems.Entity.Entitylogin;
import com.Ems.Entity.OrganizationEntity;
import com.Ems.Repository.OrganizationRepository;
import com.Ems.Repository.Reprologin;
import com.Ems.Service.Servicelogin;
import com.Ems.dto.EntityloginDTO;

import jakarta.mail.MessagingException;

@Service
public class SevriceImpLogin implements Servicelogin {

	@Autowired
	private Reprologin reprolog;

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private OrganizationRepository orgRepo;

	@Override
	public String saveUsernameAndPassword(EntityloginDTO entityDTO) {
		Optional<Entitylogin> employee = reprolog.findByUserName(entityDTO.getUserName());
		if (employee.isPresent()) {
			return "user already exists";
		}
		Entitylogin entity = new Entitylogin();
		entity.setUserName(entityDTO.getUserName());
		entity.setPassword(passwordEncoder.encode(entityDTO.getPassword()));
		entity.setRole(entityDTO.getRole());

		reprolog.save(entity);

		return "User saved successfully";
	}

//	@Override
//	public ResponseEntity<String> login(String username, String password) {
//		Optional<Entitylogin> emp = reprolog.findByUserName(username);	
//		if (passwordEncoder.matches(password, emp.get)) {
//            boolean isSuperAdmin = "superadmin".equals(emp.getRole());
//            return ResponseEntity.ok(isSuperAdmin); 
//        }
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"); // Error message
//	}
	
//	@Override
//	public ResponseEntity<String> login(String username, String password) {
//	    Optional<Entitylogin> employee = reprolog.findByUserName(username);
//	    if (employee.isPresent()) {
//	        Entitylogin emp = employee.get();
//	        if (passwordEncoder.matches(password, emp.getPassword())) {
//	            if ("superadmin".equals(emp.getRole())) {
//	                return ResponseEntity.ok("Welcome, SuperAdmin!"); 
//	            } else {
//	                return ResponseEntity.ok("Login successful! Role: " + emp.getRole()); 
//	            }
//	        }
//	    }
//	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//	    }
//	 @Override
//	    public ResponseEntity<String> login(String username, String password) {
//	        Optional<Entitylogin> employee = reprolog.findByUserName(username);
//	        if (employee.isPresent()) {
//	            Entitylogin emp = employee.get();
//	            if (passwordEncoder.matches(password, emp.getPassword())) {
//	                if ("superadmin".equals(emp.getRole())) {
//	                    return ResponseEntity.ok("Superadmin has been logged in");
//	                } else if ("admin".equals(emp.getRole())) {
//	                    Optional<OrganizationEntity> organization = orgRepo.findByContactPersonEmail(username);
//	                    if (organization.isPresent()) {
//	                        return ResponseEntity.ok( "Admin has been logged in");
//	                    } else {
//	                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials for admin");
//	                    }
//	                }
//	            }
//	        }
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//	    }
//	
	
	@Override
	public ResponseEntity<String> login(String username, String password) {
	    Optional<Entitylogin> employee = reprolog.findByUserName(username);
	    if (employee.isPresent()) {
	        Entitylogin emp = employee.get();
	        if (passwordEncoder.matches(password, emp.getPassword())) {
	            if ("superadmin".equals(emp.getRole())) {
	                return ResponseEntity.ok("Superadmin has been logged in");
	            }
	        }
	    }

	    Optional<OrganizationEntity> organization = orgRepo.findByContactPersonEmail(username);
	    if (organization.isPresent()) {
	        OrganizationEntity org = organization.get();
	        if (passwordEncoder.matches(password, org.getPassword())) {
	            if ("admin".equals(org.getRole())) { // Assuming the OrganizationEntity has a role field
	                return ResponseEntity.ok("Admin has been logged in");
	            }
	        }
	    }

	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	}

	
	


	@Override
	public Boolean updateUserPassword(String userName, String newPassword) {
		Optional<Entitylogin> employee = reprolog.findByUserName(userName);
		if (employee.isPresent()) {
			Entitylogin emp = employee.get();
			emp.setPassword(newPassword); 
			reprolog.save(emp);
			return true;
		}
		return false; 
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
		message.setText("Hello " + username + ",\n\nYour password has been reset successfully. " + "New password: "
				+ newpassword + "\n\nBest Regards,\nYour Team");
		javaMailSender.send(message);
	}

}