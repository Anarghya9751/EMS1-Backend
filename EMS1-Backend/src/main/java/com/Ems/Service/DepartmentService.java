package com.EmployeeManagementSystem.Service;

import java.util.List;

import com.EmployeeManagementSystem.Entity.DepartmentEntity;
import com.EmployeeManagementSystem.dto.DepartmentDto;




public interface DepartmentService {
    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity, Long organizationId, Integer branchId) ;



    public String deleteById(int departmentId) ;


    public DepartmentDto getDepartmentDtoById(int departmentId) ;
    
	public List<DepartmentDto> getAllDepartments() ;


    public DepartmentEntity updateDepartment(Long organizationId, Integer branchId, Integer departmentId, DepartmentDto departmentDto);

    
    public List<DepartmentDto> getDepartmentsByBranchId(Integer branchId) ;

}
