package com.EmployeeManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.Entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

	boolean existsByUsername(String username);

	boolean existsByEmailAddress(String emailAddress);

	boolean existsByContactNumber(String contactNumber);

	boolean existsByFirstName(String firstName);

	boolean existsByLastName(String lastName);

}
