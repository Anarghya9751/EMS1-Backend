package com.Ems.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ems.Entity.Entitylogin;


@Repository
public interface Reprologin extends JpaRepository<Entitylogin, Long> {
	
    Optional<Entitylogin> findByUserName(String username);

	Optional<Entitylogin> save(String username);


    
}
