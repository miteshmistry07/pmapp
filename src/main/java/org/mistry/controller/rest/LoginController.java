package org.mistry.controller.rest;

import org.mistry.config.JwtTokenUtil;
import org.mistry.model.JwtRequest;
import org.mistry.model.JwtResponse;
import org.mistry.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@CrossOrigin
	@PostMapping("/login")
	public  ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println("password: " + jwtRequest.getPassword());

		//authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		 System.out.println("**** is authenticated: " + authentication.isAuthenticated());
		
		//SecurityContext sc = SecurityContextHolder.getContext();
		//sc.setAuthentication(authentication);
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		//***** https://www.youtube.com/watch?v=egXtoL5Kg08
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		// Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		 System.out.println("is authenticated: " + authentication.isAuthenticated());
	/*	try {
			
		} 
		catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} 
		catch (LockedException e) {
			throw new Exception("LOCKED EXCEPTION", e);
		}
		catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
*/
	}
}
