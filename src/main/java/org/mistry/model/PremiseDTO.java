package org.mistry.model;

import org.mistry.entity.Premise;

public class PremiseDTO {
	
	private int premiseId;
	private String premiseNumber;
	private String address;
    private String city;
    private String postCode;
	
	public PremiseDTO() {
		
	}
	
	public PremiseDTO(Premise premise) {
		this.premiseId = premise.getPremiseId();
		this.premiseNumber = premise.getPremiseNumber();
		this.address = premise.getAddress();
		this.city = premise.getCity();
		this.postCode = premise.getPostCode();
	}
}
