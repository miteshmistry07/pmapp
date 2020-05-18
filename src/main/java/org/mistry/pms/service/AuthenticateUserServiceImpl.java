package org.mistry.pms.service;

import org.mistry.pms.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserServiceImpl implements AuthenticateUserService {
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	public String authenticateUser(String username, String password) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		//System.out.println("Authenticated: " + authentication.isAuthenticated());
		
		//set the security context holder so we can get the username of the person logged in
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		
		return token;
	}

	

}
