package org.mistry.pms.controller.rest;

import org.mistry.pms.model.request.JwtRequest;
import org.mistry.pms.model.response.JwtResponse;
import org.mistry.pms.service.AuthenticateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class LoginController {

	@Autowired
	private AuthenticateUserService authenticateUserService;
		
	@CrossOrigin
	@PostMapping("/login")
	public  ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		//System.out.println("jwtRequest: " + jwtRequest.toString());
		final String token = authenticateUserService.authenticateUser(jwtRequest.getUsername(), jwtRequest.getPassword());
		
		//***** https://www.youtube.com/watch?v=egXtoL5Kg08
		return ResponseEntity.ok(new JwtResponse(token));
	}

}
