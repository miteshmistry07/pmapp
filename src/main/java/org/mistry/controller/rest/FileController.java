package org.mistry.controller.rest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.mistry.entity.DbFiles;
import org.mistry.entity.Premise;
import org.mistry.model.FileDTO;
import org.mistry.model.FileDTO2;
import org.mistry.model.FilesImagesDTO;
import org.mistry.payload.UploadFileResponse;
import org.mistry.service.FileService;
import org.mistry.service.PremiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api/file")
@RestController
public class FileController {
	
	@Autowired
    private FileService fileService;
	
	@Autowired
	private PremiseService premiseService;
    
	@CrossOrigin
	@PostMapping("/upload")
	public UploadFileResponse uploadFile(@PathVariable("file") MultipartFile file) {
	   
		String fileName = fileService.storeFile(file);
	
	    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	            .path("/downloadFile/")
	            .path(fileName)
	            .toUriString();
	
	    return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	@CrossOrigin
	@PostMapping("/uploadPremiseImages")
	public UploadFileResponse uploadPremises(@PathVariable("file") MultipartFile file,
			@RequestParam("description") String description, @RequestParam("category") String category, 
			@RequestParam(name = "premiseId", required = true) int premiseId) throws IOException {
	   
		String fileName = fileService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	            .path("/downloadFile/")
	            .path(fileName)
	            .toUriString();
		
		//get the premise from the premiseId parameter
		Optional<Premise> prem = premiseService.getPremiseById(premiseId);
		
		if (prem.isPresent()) {
			Premise premise = prem.get();
			DbFiles dbFile = new DbFiles(premise, file.getBytes(), description, category, file.getContentType(), file.getOriginalFilename());
			fileService.storeFileInDb(dbFile);
		}
		
		
	    return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	// https://www.baeldung.com/spring-controller-return-image-file
	
	@CrossOrigin
	@GetMapping(value="/getAllFiles/{premiseId}")
	public Iterable<FileDTO2> getPremiseFiles(@PathVariable("premiseId") int premiseId) {
		
		//get the premise using ID 
		Optional<Premise> prem = premiseService.getPremiseById(premiseId);
			
		if (prem.isPresent()) {
			
			Premise premise = prem.get();
			//System.out.println("premise id: " + premiseId);
			List<DbFiles> list =  fileService.retriveFiles(premise);
			
			return list.stream()
							.map(c -> new FileDTO2(c))
							.collect(Collectors.toList());
		}
		//pass the premiseObj to the DBFile
		return null;
	}
	
	//return all files with category = image
	@CrossOrigin
	@GetMapping(value="/getImages/{premiseId}")
	public FilesImagesDTO getPremiseImages(@PathVariable("premiseId") int premiseId) {
		
		List<DbFiles> list = fileService.retrieveImages(premiseId);
		
		/*
		 * return list.stream() .map(c -> new FileDTO2(c))
		 * .collect(Collectors.toList());
		 */
		
		
		 FilesImagesDTO files = new FilesImagesDTO(list); 
		 return files;	
		//need to return something
	}
	
}
