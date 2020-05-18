package org.mistry.pms.controller.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.mistry.pms.entity.Premise;
import org.mistry.pms.model.PremiseDTO;
import org.mistry.pms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/premise")
@RestController
public class PremiseController {
	//http://localhost:8080/api/premise
	
	@Autowired 
	private PremiseService premiseService;
	
	//create
	@CrossOrigin
	@PostMapping("/add")
	public Premise createPremise(@Valid @RequestBody Premise premise) {
		//create a new premise
		//System.out.println("Create premise: " + premise.toString() );
		premiseService.savePremise(premise);
		return premise;
	}

	//read
	@CrossOrigin
	@GetMapping("/{premiseId}")
	public Optional<Premise> getPremiseById(@PathVariable int premiseId) {
		//get a premise details using the premise id
		//System.out.println("getPremiseById" + premiseId);
		return premiseService.getPremiseById(premiseId);
	}
	
	//update 
	@CrossOrigin
	@PutMapping("/update")
	public Premise updatePremise(@RequestBody Premise premise) {
		premiseService.savePremise(premise);
		return null;
	}
		
	//delete
	@CrossOrigin
	@DeleteMapping("/delete/{premiseId}")
	public void deletePremise(@PathVariable int premiseId) {
		//delete a premise given the premiseId
		premiseService.deletePremise(premiseId);
	}
	
	@CrossOrigin
	@GetMapping("/list") 
	public Iterable<Premise>  all() {
		//list of all premises
		return premiseService.getAllPremises();
	} 
		
	@CrossOrigin
	@GetMapping("/page")
	public Iterable<Premise> pageOfPremise(@RequestParam(value="pageNo", required=false, defaultValue="0") int currentPage, 
										   @RequestParam(value="pageSize", required=false, defaultValue="3") int pageSize,
										   @RequestParam(value="sort", required=false, defaultValue="asc") String order) {
		//paged result set of premises
		Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("premiseId").ascending());
		return premiseService.getPage(pageable);
		
	}
	//https://www.baeldung.com/rest-api-pagination-in-spring
	
	
	/*@CrossOrigin
	@RequestMapping("/")
	public String home() {
		return "Hello World!";
	}*/
	
}
