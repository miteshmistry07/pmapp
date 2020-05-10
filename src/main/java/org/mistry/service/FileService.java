package org.mistry.service;

import java.util.List;

import org.mistry.entity.DbFiles;
import org.mistry.entity.Premise;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.mistry.model.FileDTO;
import org.mistry.model.FileDTO2;

public interface FileService {
	
	public String storeFile(MultipartFile file);
	public Resource loadFileAsResource(String fileName);
	public String storeFileInDb(DbFiles file);
	public List<DbFiles> retriveFiles(Premise premise);
	public List<DbFiles> retrieveImages(int premiseId); //return images with a category of Images
	public FileDTO getFile(Premise premise);
}
