package com.Ems.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Ems.Entity.BranchEntity;
import com.Ems.Entity.DepartmentEntity;



@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

	List<DepartmentEntity> findByBranch(BranchEntity branch);

	
	



	



	
}
