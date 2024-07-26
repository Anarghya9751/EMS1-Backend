package com.ems.Repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.Entity.SubDepartmentEntity;

@Repository
public interface SubDepartmentRepository  extends JpaRepository<SubDepartmentEntity, Long> {

}
