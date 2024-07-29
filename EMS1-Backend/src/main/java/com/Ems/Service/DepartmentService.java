package com.Ems.Service;

import java.util.List;

import com.Ems.Entity.DepartmentEntity;
import com.Ems.dto.DepartmentDto;




public interface DepartmentService {
    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity, Long organizationId, Integer branchId) ;



    public String deleteById(int departmentId) ;


    
	

    public DepartmentEntity updateDepartment(Long organizationId, Integer branchId, Integer departmentId, DepartmentDto departmentDto);

	public DepartmentDto getDepartmentById(int departmentId) ;
    
    public List<DepartmentDto> getAllDepartments();
    
	public DepartmentEntity getDepartmentById1(Integer departmentId) ;


}
