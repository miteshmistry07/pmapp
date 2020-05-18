package org.mistry.pms.config;

import org.mistry.pms.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //use roles on controller methods
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService; //implements UserDetailsService - tell DaoAuthenticationConfigurer what to use
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter; //for filtering all requests

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials, Use BCryptPasswordEncoder
		//DaoAuthenticationConfigurer daoAuthenticationConfigurer = auth.userDetailsService(myUserDetailsService));//.passwordEncoder(passwordEncoder());
		auth.userDetailsService(myUserDetailsService).passwordEncoder(getPasswordEncoder());
	}
	
	//custom password encoder need to change to use bcrypt hashing
	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				//System.out.println("encoding: " + charSequence.toString());
				return charSequence.toString();
			}
			
			@Override
			public boolean matches(CharSequence charSequence, String s) {
				String charStr = new String(charSequence.toString());
				//System.out.println("matches call: " + charStr + ", string: " + s);
				
				if (s.equalsIgnoreCase(charStr)) {
					//System.out.println("password match");
					return true;
				}
				return false;
			}
		};
	}
	
	/*
	 * @Bean 
	 * public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable(); // We don't need CSRF for this example	
		httpSecurity.authorizeRequests().antMatchers("/api/login").permitAll(); // don't authenticate this particular request
		// all other requests need to be authenticated
		httpSecurity.authorizeRequests().anyRequest().authenticated().and()
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
			//Stateless session so it won't be used to store user's state and skip Spring security filter chain
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); 		
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}