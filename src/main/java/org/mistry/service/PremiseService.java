package org.mistry.service;

import java.util.Optional;

import org.mistry.entity.Premise;
import org.springframework.data.domain.Pageable;


public interface PremiseService {
	
	public Iterable<Premise> getAllPremises();
	
	public Iterable<Premise> getPage(Pageable page);
	
	public void savePremise(Premise premise);
	
	public void deletePremise(int premiseId);
	
	public Optional<Premise> getPremiseById(int premiseId);
	
}
