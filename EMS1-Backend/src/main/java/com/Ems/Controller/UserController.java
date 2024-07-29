package com.Ems.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Ems.Entity.Entitylogin;
import com.Ems.Service.Servicelogin;
import com.Ems.dto.EntityloginDTO;

import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;

@RestController
@CrossOrigin("****")
@RequestMapping("api/User")
public class UserController {

	@Autowired
    private Servicelogin userService;
	@PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestParam String userName,
                                           @RequestParam String password,
                                           @RequestParam String role) {
        EntityloginDTO entityDTO = new EntityloginDTO();
        entityDTO.setUserName(userName);
        entityDTO.setPassword(password);
        entityDTO.setRole(role);

        String result = userService.saveUsernameAndPassword(entityDTO);
        return ResponseEntity.ok(result);
    }
	    @PostMapping("/login")
	    public ResponseEntity<Boolean> login(@RequestParam String userName,
	                                         @RequestParam String password) {
	        return userService.login(userName, password);
	    }

	    @PutMapping("/updatePassword")
	    public ResponseEntity<Boolean> updatePassword(@RequestParam String userName,
	                                                  @RequestParam String newPassword) {
	        boolean isUpdated = userService.updateUserPassword(userName, newPassword);
	        return ResponseEntity.ok(isUpdated);
	    }

	    @PostMapping("/sendPasswordReset")
	    public ResponseEntity<Void> sendPasswordReset(@RequestParam String email,
	                                                  @RequestParam String userName,
	                                                  @RequestParam String newPassword) {
	        try {
	        	userService.sendPasswordReset(email, userName, newPassword);
	            return ResponseEntity.ok().build();
	        } catch (Exception e) {
	            return ResponseEntity.status(500).build();
	        }
	    }
}
