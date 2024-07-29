package com.Ems.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ems.Entity.RoleEntity;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

	boolean existsByRoleNameAndRoleDescriptionAndDepartmentDepartmentIdAndSubdepartmentSubDepartmentId(String roleName,
			String roleDescription, Integer departmentId, Long subDepartmentId);

}
