package org.mistry.pms.config;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
	
	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		//authentication errors all come to this class - set from WebSecurityConfiguration class
		//System.out.println(authException.getLocalizedMessage() + authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}

	
}
