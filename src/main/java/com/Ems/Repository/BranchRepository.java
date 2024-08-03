package com.Ems.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ems.Entity.BranchEntity;
import com.Ems.Entity.OrganizationEntity;


@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

	List<BranchEntity> findByOrganization(OrganizationEntity organization);

	
	Optional<BranchEntity> findByBranchNameAndBranchDescriptionAndBranchAddressAndBranchContactNumber(String branchName,
			String branchDescription, String branchAddress, String branchContactNumber);

}
