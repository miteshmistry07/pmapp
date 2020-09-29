package org.mistry.pms.controller.validation;

public class PremiseNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PremiseNotFoundException(int id) {
		super("Could not find Premise with ID: " + id);
		
	}

}	
