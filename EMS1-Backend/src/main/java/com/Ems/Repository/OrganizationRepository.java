package com.Ems.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ems.Entity.OrganizationEntity;


@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

	boolean existsByOrganizationNameAndOrganizationTypeAndLocationAndContactPersonNameAndContactPersonEmailAndContactPersonPhoneNumber(
			String organizationName, String organizationType, String location, String contactPersonName,
			String contactPersonEmail, String contactPersonPhoneNumber);

	Optional<OrganizationEntity> findByContactPersonEmail(String username);




}
