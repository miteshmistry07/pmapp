package org.mistry.pms.service;

import java.util.Optional;

import org.mistry.pms.controller.validation.PremiseNotFoundException;
import org.mistry.pms.entity.Premise;
import org.mistry.pms.repository.PremiseRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
		//return PremiseNotFoundException
		Optional<Premise> premise = premiseRepository.findById(premiseId);
		
		if (!premise.isPresent()) {
			premise.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Premise ID: " + premiseId, new PremiseNotFoundException(premiseId)));
		}
		
		return premise;
	}
	
	@Override
	public void saveExistingPremise(Premise premiseToSave) {
		
		int premiseId = premiseToSave.getPremiseId(); //get the premise ID from request body 
		Optional<Premise> premise = this.getPremiseById(premiseId);
		
		//get the Premise Id and check if it exists..
		if (premise.isPresent()) {
			premiseRepository.save(premiseToSave);
		}
		else {
			//premise.orElseThrow(() -> new PremiseNotFoundException(premiseToSave.getPremiseId()));
			 premise.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Premise ID: " + premiseToSave.getPremiseId(), new PremiseNotFoundException(premiseToSave.getPremiseId())));
			 //https://www.baeldung.com/spring-response-status-exception
		}
		
		//https://stackabuse.com/guide-to-spring-data-jpa/
		//https://spring.io/guides/tutorials/rest/
		
		
		
	}
}
