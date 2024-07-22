package com.EmployeeManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.Entity.BranchEntity;
import com.EmployeeManagementSystem.Entity.DepartmentEntity;



@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

	List<DepartmentEntity> findByBranch(BranchEntity branch);

}
