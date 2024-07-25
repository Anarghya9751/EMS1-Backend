package com.EmployeeManagementSystem.Service;

import java.util.List;

import com.EmployeeManagementSystem.Entity.SubDepartmentEntity;
import com.EmployeeManagementSystem.dto.SubDepartmentDto;



public interface SubDepartmentService {

	SubDepartmentEntity saveSubDepartment(SubDepartmentDto subDepartmentDto, int departmentId);


    String deleteSubDepartment(Long subDepartmentId);

    List<SubDepartmentDto> getAllSubDepartments();

    SubDepartmentDto getSubDepartmentDtoById(Long subDepartmentId);
    
    public SubDepartmentEntity updateSubDepartment(Long subDepartmentId, SubDepartmentEntity subDepartmentEntity);

}
