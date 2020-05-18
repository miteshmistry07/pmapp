package org.mistry.pms.model;

import java.util.List;
import java.util.stream.Collectors;

import org.mistry.pms.entity.DbFiles;
import org.mistry.pms.entity.Premise;

public class FilesImagesDTO {
	
	//return an array of images with a result set information
	private int count;
	private List<FileDTO2> dbFiles;
	private Premise premise;
	
	
	public FilesImagesDTO(List<DbFiles> files) {
		
		//this.premise = getPremise().getPremise
		this.count = files.size();
		this.dbFiles = files.stream()
						.map(c -> new FileDTO2(c))
						.collect(Collectors.toList());
		
	}

	public List<FileDTO2> getDbFiles() {
		return dbFiles;
	}

	public void setDbFiles(List<FileDTO2> dbFiles) {
		this.dbFiles = dbFiles;
	}

	public Premise getPremise() {
		return premise;
	}

	public void setPremise(Premise premise) {
		this.premise = premise;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
