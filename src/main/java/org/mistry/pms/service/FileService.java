package org.mistry.pms.service;

import java.util.List;

import org.mistry.pms.entity.DbFiles;
import org.mistry.pms.entity.Premise;
import org.mistry.pms.model.FileDTO;
import org.mistry.pms.model.FileDTO2;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	public String storeFile(MultipartFile file);
	public Resource loadFileAsResource(String fileName);
	public String storeFileInDb(DbFiles file);
	public List<DbFiles> retriveFiles(Premise premise);
	public List<DbFiles> retrieveImages(int premiseId); //return images with a category of Images
	public FileDTO getFile(Premise premise);
}
