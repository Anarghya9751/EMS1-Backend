package com.Ems.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ems.Entity.OrganizationEntity;


@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

	boolean existsByOrganizationNameAndOrganizationTypeAndLocationAndContactPersonNameAndContactPersonEmailAndContactPersonPhoneNumber(
			String organizationName, String organizationType, String location, String contactPersonName,
			String contactPersonEmail, String contactPersonPhoneNumber);


	boolean existsByOrganizationName(String organizationName);

	boolean existsByRegistrationNumber(String registrationNumber);

	boolean existsByContactPersonEmail(String contactPersonEmail);

	boolean existsByContactPersonPhoneNumber(String contactPersonPhoneNumber);


	boolean existsByContactPersonName(String contactPersonName);

//	boolean existsByWebsiteURL(String websiteURL);

}
