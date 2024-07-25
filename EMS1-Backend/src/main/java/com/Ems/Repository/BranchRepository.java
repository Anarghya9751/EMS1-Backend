package com.EmployeeManagementSystem.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.Entity.BranchEntity;
import com.EmployeeManagementSystem.Entity.OrganizationEntity;


@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

	List<BranchEntity> findByOrganization(OrganizationEntity organization);

	
	Optional<BranchEntity> findByBranchNameAndBranchDescriptionAndBranchAddressAndBranchContactNumber(String branchName,
			String branchDescription, String branchAddress, String branchContactNumber);

}
