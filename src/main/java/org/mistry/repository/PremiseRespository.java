package org.mistry.repository;

import org.mistry.entity.Premise;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremiseRespository extends JpaRepository<Premise, Integer> {
	
	//public Itera findAll(Pageable pageable);
}
