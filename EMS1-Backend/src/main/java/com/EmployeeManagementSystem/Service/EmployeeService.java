package com.EmployeeManagementSystem.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.EmployeeManagementSystem.Entity.EmployeeEntity;
import com.EmployeeManagementSystem.dto.EmployeeDTO;


public interface EmployeeService {

    public String saveEmployee(EmployeeEntity employee, Integer branchId, Integer departmentId, Long subDepartmentId, MultipartFile profileImage) throws IOException ;


    EmployeeDTO findById(Long userId);

    void saveEmployee(EmployeeDTO employeeDTO);

    void updateProfile(EmployeeDTO employeeDTO);

    void changePassword(Long userId, String password);

    void updateProfileImage(Long userId, MultipartFile profileImages) throws Exception;
    
    List<EmployeeDTO> getAllEmployees();


    public String deleteEmployee(Long employeeId) ;

}
