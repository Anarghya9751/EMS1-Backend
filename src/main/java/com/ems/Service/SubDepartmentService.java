
package com.ems.Service;

import java.util.List;

import com.ems.Entity.SubDepartmentEntity;
import com.ems.dto.SubDepartmentDTO;


public interface SubDepartmentService {

	SubDepartmentEntity saveSubDepartment(SubDepartmentDTO subDepartmentDto, Long departmentId);

    SubDepartmentEntity updateSubDepartment(Long subDepartmentId, SubDepartmentDTO subDepartmentDto);

    String deleteSubDepartment(Long subDepartmentId);

    List<SubDepartmentDTO> getAllSubDepartments();

    SubDepartmentDTO getSubDepartmentDtoById(Long subDepartmentId);
}
