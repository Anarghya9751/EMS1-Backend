package com.Ems.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ems.Entity.OrganizationEntity;


@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

}
