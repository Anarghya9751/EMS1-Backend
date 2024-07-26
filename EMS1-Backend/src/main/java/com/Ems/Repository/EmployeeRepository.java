package com.Ems.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ems.Entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

	boolean existsByUsername(String username);

	boolean existsByEmailAddress(String emailAddress);

	boolean existsByContactNumber(String contactNumber);

	boolean existsByFirstName(String firstName);

	boolean existsByLastName(String lastName);

}
