package com.ems.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.Entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>{

}
