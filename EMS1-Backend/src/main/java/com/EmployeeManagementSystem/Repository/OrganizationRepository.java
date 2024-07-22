package com.EmployeeManagementSystem.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagementSystem.Entity.OrganizationEntity;


@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {

}
