package org.mistry.pms.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="premise")
public class Premise {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PremiseId")
	private int premiseId;
	
	@OneToMany(mappedBy="premise")
	private Set<DbFiles> dbFiles;
	
	@Column(name="PremiseNumber")
	@NotNull()
	@Size(min=1, message="Premise number/name is mandatory")
	private String premiseNumber;
	
	@NotNull()
	@Size(min=1, message="Address is mandatory")
	@Column(name="Address")
    private String address;
    
	@NotNull()
	@Size(min=1, message="City is mandatory")
	@Column(name="City")
    private String city;
    
	@NotNull()
	@Size(min=1, message="Post code is mandatory")
	@Column(name="PostCode")
    private String postCode;

	public int getPremiseId() {
		return premiseId;
	}

	public void setPremiseId(int premiseId) {
		this.premiseId = premiseId;
	}

	public String getPremiseNumber() {
		return premiseNumber;
	}

	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Override
	public String toString() {
		return "Premise [premiseId=" + premiseId + ", premiseNumber=" + premiseNumber + ", address=" + address
				+ ", city=" + city + ", postCode=" + postCode + "]";
	}
	
}
