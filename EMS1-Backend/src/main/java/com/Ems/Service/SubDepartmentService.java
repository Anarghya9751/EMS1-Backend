package com.Ems.Service;

import java.util.List;

import com.Ems.Entity.SubDepartmentEntity;
import com.Ems.dto.SubDepartmentDto;



public interface SubDepartmentService {

	SubDepartmentEntity saveSubDepartment(SubDepartmentDto subDepartmentDto, int departmentId);


    String deleteSubDepartment(Long subDepartmentId);


    public List<SubDepartmentEntity> getAllSubDepartments() ;
	public SubDepartmentEntity getSubDepartmentById(Long subDepartmentId) ;


    public SubDepartmentEntity updateSubDepartment(Long subDepartmentId, SubDepartmentEntity subDepartmentEntity);

}
