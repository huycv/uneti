package edu.uneti.web.auth.dto;

import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class AuthUserDto {

	/** The username */
	@Getter
	private final String username;
	
	/** The password */
	private final String password;
	
	public AuthUserDto(@JsonProperty("username") String username, @JsonProperty("password") String password) {
		this.username = username.trim();
		this.password = username.trim();
	}

	public Optional<String> getPassword() {
		return Optional.ofNullable(password).map(p -> new BCryptPasswordEncoder().encode(p));
	}
	
	public UsernamePasswordAuthenticationToken toAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(username, password);
	}


	
}
