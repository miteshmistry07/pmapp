package org.mistry.pms.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.mistry.pms.config.FileStorageProperties;
import org.mistry.pms.entity.DbFiles;
import org.mistry.pms.entity.Premise;
import org.mistry.pms.model.FileDTO;
import org.mistry.pms.model.FileDTO2;
import org.mistry.pms.payload.UploadFileResponse;
import org.mistry.pms.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
	
	private final Path fileStorageLocation;
	
	@Autowired
    private FileRepository fileRepository;
	
	@Autowired
	private PremiseService premiseService;
	    
    @Autowired
    public FileServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
           // throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    
   
    @Override
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                //throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
            
        } catch (IOException ex) {
            //throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        	return null;
        }
        
    }
    
    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                //throw new MyFileNotFoundException("File not found " + fileName);
            	return null;
            }
        } catch (MalformedURLException ex) {
            //throw new MyFileNotFoundException("File not found " + fileName, ex);
        	return null;
        }
    }
    
    @Override
    public String storeFileInDb(DbFiles file) {
    	
    	/* default setting mySQL  
    	 ********** do not delete *********************
    		max allowed packet =1048576 
    		
    		3048448
    	*/
    	
    	//String fileName = StringUtils.cleanPath(fileToSave.getOriginalFilename());
    	  fileRepository.save(file);
          return "saved";
    	 /* 
         try {
             // Check if the file's name contains invalid characters
             if(fileName.contains("..")) {
                 //throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
             }

             // Copy file to the target location (Replacing existing file with the same name)
             // Path targetLocation = this.fileStorageLocation.resolve(fileName);
             //Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
             
             //DbFiles file = new DbFiles(premiseId, fileToSave.getBytes(), fileDTO.getDescription(), fileDTO.getDescriptionType());

           
             
         } catch (IOException ex) {
             //throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
         	return null;
         }*/
    }


	@Override
	public List<DbFiles> retriveFiles(Premise premise) {
		// TODO Auto-generated method stub
		
		List<DbFiles> files = fileRepository.findAllByPremise(premise);
		
		return files;
		
	}


	@Override 
	public List<DbFiles> retrieveImages(int premiseId) {
		// TODO Auto-generated method stub
		
		//get the premise using the premise ID
		Optional<Premise> optPremise = premiseService.getPremiseById(premiseId);
		
		if (optPremise.isPresent()) {
			Premise premise = optPremise.get();
			List<DbFiles> files = fileRepository.findAllByPremiseAndCategory(premise, "receipt");
			System.out.println(files.size());
			return files;
		}
		
		return null;
	}


	@Override
	public FileDTO getFile(Premise premise) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
