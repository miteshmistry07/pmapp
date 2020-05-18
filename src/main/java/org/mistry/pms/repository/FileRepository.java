package org.mistry.pms.repository;

import java.util.List;

import org.mistry.pms.entity.DbFiles;
import org.mistry.pms.entity.Premise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<DbFiles, Integer> {
	
	public List<DbFiles>findAllByPremise(Premise premise);
	public List<DbFiles>findAllByPremiseAndCategory(Premise premise, String category);
}
