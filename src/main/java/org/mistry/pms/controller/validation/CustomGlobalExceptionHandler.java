package org.mistry.pms.controller.validation;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html#mvc-ann-rest-spring-mvc-exceptions


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler  {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException  ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		
		//Get all errors
		List<String> errors = ex.getBindingResult()
		        .getFieldErrors()
		        .stream()
		        .map(x -> x.getDefaultMessage())
		        .collect(Collectors.toList());
		
		body.put("errors", errors);

		return new ResponseEntity<>(body, headers, status);
	}
	
	/*
	 * @Override public ResponseEntity<Object> handleException(Exception ex,
	 * WebRequest request) {
	 * 
	 * return null; }
	 */
}