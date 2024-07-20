package com.ems.Service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.ems.Entity.Entitylogin;
import com.ems.dto.EntityloginDTO;

import jakarta.mail.MessagingException;

public interface Servicelogin {
	
    boolean saveUsernameAndPassword(EntityloginDTO entityDTO);

    ResponseEntity<Boolean> login(String username, String password);
    

	void sendPasswordReset(String string, String username,String newpassword) throws MessagingException;

	
	Boolean updateUserPassword(String userName, String newPassword);
	
	    Optional<Entitylogin> findByUsername(String email);
	    
	    void save(Entitylogin employee);






	


	
   
   
    
    
    

    
}
