package com.Ems.Service;

import java.util.Optional;
import org.springframework.http.ResponseEntity;

import com.Ems.Entity.Entitylogin;
import com.Ems.dto.EntityloginDTO;

import jakarta.mail.MessagingException;




public interface Servicelogin {
	
    public String saveUsernameAndPassword(EntityloginDTO entityDTO) ;

    public ResponseEntity<String> login(String username, String password) ;
    

	void sendPasswordReset(String string, String username,String newpassword) throws MessagingException;

	
	Boolean updateUserPassword(String userName, String newPassword);
	
	    Optional<Entitylogin> findByUsername(String email);
	    
	    void save(Entitylogin employee);






	


	
   
   
    
    
    

    
}
