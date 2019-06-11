package edu.uneti.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uneti.web.auth.AppUserDetailsService;
import edu.uneti.web.auth.TokenAuthService;
import edu.uneti.web.auth.UserAuthentication;
import edu.uneti.web.auth.dto.AuthUserDto;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	/** The token authentication service */
	private final TokenAuthService tokenAuthenticationService;
	
	/** The user service */
	private final AppUserDetailsService userService;
	
	/**
	 * Instantiates a new stateless login filter
	 * 
	 * @param urlMapping
	 *            the url mapping
	 * @param tokenAuthenticationService
	 *            the token authentication service
	 * @param userService
	 *            the user service
	 * @param authenticationManager
	 *            the authentication manager
	 */
	public StatelessLoginFilter(String urlMapping, TokenAuthService tokenAuthenticationService,
			AppUserDetailsService userService, AuthenticationManager authenticationManager) {
		super(urlMapping);
		this.tokenAuthenticationService = tokenAuthenticationService;
		this.userService = userService;
		setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
	        throws AuthenticationException, IOException, ServletException {
		if (!request.getMethod().equals("POST")) {
			return null;
		}
		final AuthUserDto user = this.toUser(request);
		final UsernamePasswordAuthenticationToken loginToken = user.toAuthenticationToken(); 
		return getAuthenticationManager().authenticate(loginToken);
	}
	
	private AuthUserDto toUser(HttpServletRequest request) throws IOException {
		return new ObjectMapper().readValue(request.getInputStream(), AuthUserDto.class);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		final UserDetails authenticatedUser = this.userService.loadUserByUsername(authResult.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser); 
		this.tokenAuthenticationService.addJwtTokenToHeader(response, userAuthentication);
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
	}

}
