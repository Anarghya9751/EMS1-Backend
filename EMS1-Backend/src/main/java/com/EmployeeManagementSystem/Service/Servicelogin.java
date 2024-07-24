package com.EmployeeManagementSystem.Service;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import com.EmployeeManagementSystem.Entity.Entitylogin;
import com.EmployeeManagementSystem.dto.EntityloginDTO;

import jakarta.mail.MessagingException;




public interface Servicelogin {
	
    public String saveUsernameAndPassword(EntityloginDTO entityDTO) ;

    ResponseEntity<Boolean> login(String username, String password);
    

	void sendPasswordReset(String string, String username,String newpassword) throws MessagingException;

	
	Boolean updateUserPassword(String userName, String newPassword);
	
	    Optional<Entitylogin> findByUsername(String email);
	    
	    void save(Entitylogin employee);






	


	
   
   
    
    
    

    
}
