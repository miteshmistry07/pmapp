package org.mistry.model;

import org.mistry.entity.DbFiles;
import org.mistry.entity.Premise;
import org.springframework.beans.BeanUtils;

public class FileDTO2 {
	
	private int id;
	private int premiseId;
	private String description;
	/*
	 * private String category; private String fileType; private String fileName;
	 *///private byte[] data;
	
	//Constructor
	public FileDTO2(DbFiles dbFile) {	
		BeanUtils.copyProperties(dbFile, this);
		this.premiseId = dbFile.getPremise().getPremiseId();
	}
/*			
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
*/
	public int getPremiseId() {
		return premiseId;
	}

	public void setPremiseId(int premiseId) {
		this.premiseId = premiseId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*
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
	*/
	
}
