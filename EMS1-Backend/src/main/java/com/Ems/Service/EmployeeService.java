package com.Ems.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.Ems.Entity.EmployeeEntity;
import com.Ems.dto.EmployeeDTO;


public interface EmployeeService {

	public String saveEmployee(EmployeeEntity employee, Long roleId, MultipartFile profileImages) throws IOException;


    public List<EmployeeEntity> getAllEmployees() ;



    void changePassword(Long userId, String password);

    void updateProfileImage(Long userId, MultipartFile profileImages) throws Exception;
    
	 public EmployeeEntity updateEmployee(Long userId, EmployeeEntity employeeUpdates) ;

	    public EmployeeEntity getEmployeeById(Long userId) ;

    public String deleteEmployee(Long employeeId) ;

}
