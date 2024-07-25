package com.EmployeeManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.Entity.SubDepartmentEntity;


@Repository
public interface SubDepartmentRepository  extends JpaRepository<SubDepartmentEntity, Long> {

	boolean existsBySubDepartmentNameAndParentDepartmentDepartmentId(String subDepartmentName, int departmentId);

}
