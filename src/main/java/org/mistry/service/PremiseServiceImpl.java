package org.mistry.service;

import java.util.Optional;

import org.mistry.entity.Premise;
import org.mistry.repository.PremiseRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PremiseServiceImpl implements PremiseService {
	
	@Autowired
	private PremiseRespository premiseRepository;
	
	//returns a complete list of premises (not paged)
	@Override
	public Iterable<Premise> getAllPremises() {
		//return all premises.
		return premiseRepository.findAll(Sort.by(Sort.Direction.ASC, "premiseId") );
		
	}
	
	//returns a page of premises
	@Override
	public Iterable<Premise> getPage(Pageable page) {
		return premiseRepository.findAll(page);
				//https://stackabuse.com/guide-to-spring-data-jpa/
	}
	
	//creates a new premise
	@Override
	public void savePremise(Premise premise) {
		 premiseRepository.save(premise);
	}
	
	@Override
	public void deletePremise(int premiseId) {
		
		premiseRepository.deleteById(premiseId);
	}
	
	@Override
	public Optional<Premise> getPremiseById(int premiseId) {
		
		return premiseRepository.findById(premiseId);
	}
}
