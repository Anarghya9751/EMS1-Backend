package com.ems.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.Entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
