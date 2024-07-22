package com.EmployeeManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.Entity.BranchEntity;
import com.EmployeeManagementSystem.Entity.OrganizationEntity;


@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

	List<BranchEntity> findByOrganization(OrganizationEntity organization);

}
