package org.mistry.pms.controller.validation;

import org.mistry.pms.config.DateTimeUtilities;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PremiseNotFoundAdvice {

	//@ResponseBody
	//@ExceptionHandler(PremiseNotFoundException.class) // using ResponseStatusException instead so don't require this anymore
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> employeeNotFoundHandler(PremiseNotFoundException ex) {
		    
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new DateTimeUtilities().defaultDateTime()); //use date time utils to get legible date/time
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("errors", ex.getMessage());
		body.put("path", "need dynamic path");// ***** sort this out ********
		// more information on how to resolve the errors - https://www.baeldung.com/exception-handling-for-rest-with-spring
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		
	}
}
