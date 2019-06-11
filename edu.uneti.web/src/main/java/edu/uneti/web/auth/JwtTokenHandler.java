package edu.uneti.web.auth;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import edu.uneti.core.auth.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public final class JwtTokenHandler {
	
	@Value("${bsb.jwt.secret}")
	private String secret;
	
	@Autowired
	private AppUserDetailsService applicationUserService;

	@Autowired
	private UserService userService;
	
	Optional<UserDetails> parseUserFromToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
		String username = claims.get("username", String.class);
		return Optional.ofNullable(applicationUserService.loadUserByUsername(username));
	}

	public String createTokenForUser(UserDetails user) {
		final ZonedDateTime afterOnWeek = ZonedDateTime.now().plusWeeks(1);
		List<? extends GrantedAuthority> roles = new ArrayList<>(user.getAuthorities());
		return Jwts.builder()
				.claim("username", user.getUsername())
				.claim("displayName", this.userService.getDisplayName(user.getUsername()))
				.claim("role", roles.isEmpty() ? null : roles.get(0).getAuthority())
				.signWith(SignatureAlgorithm.HS512, secret)
				.setExpiration(Date.from(afterOnWeek.toInstant()))
				.compact();
	}
}
