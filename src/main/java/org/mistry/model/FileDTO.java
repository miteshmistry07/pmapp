package org.mistry.model;

import org.mistry.entity.Premise;

public class FileDTO {
	
	private int premiseId;
	private String description;
	private String category; 
	private String fileType;
	private String fileName;
	

	public FileDTO(Premise premise, String description, String category, String fileType, String fileName ) {
		
		this.premiseId = premise.getPremiseId();
		this.description = description;
		this.category = category;
		this.fileType = fileType;
		this.fileName = fileName;
	}


	public int getPremiseId() {
		return premiseId;
	}


	public void setPremiseId(int premiseId) {
		this.premiseId = premiseId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	
}
