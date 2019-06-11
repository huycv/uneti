package edu.uneti.web.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import edu.uneti.web.auth.AppUserDetailsService;
import edu.uneti.web.auth.TokenAuthService;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenAuthService tokenAuthenticationService;
	
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/admin/css/**", "/admin/img/**", "/admin/js/**");
	}
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(appUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", TokenAuthService.AUTH_HEADER_NAME, "x-file-name"));
		configuration.setExposedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", TokenAuthService.AUTH_HEADER_NAME, "x-file-name"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		        .and()
		        .authorizeRequests()
		        .antMatchers(HttpMethod.GET, "/webapi/image/tire/*").permitAll()
		        .antMatchers(HttpMethod.GET, "/webapi/home/current-time").permitAll()
		        .antMatchers(HttpMethod.GET, "/webapi/home/test").permitAll()
		        .antMatchers(HttpMethod.GET, "/webapi/home/test2").permitAll()
		        .antMatchers(HttpMethod.POST, "/webapi/user/find-by-username-or-email").permitAll()
		        .antMatchers(HttpMethod.PATCH, "/webapi/user/change-password/*").permitAll()
		        .antMatchers("/webapi", "/webapi/**").authenticated()
		        .antMatchers("/**", "/favicon.ico").permitAll();
	}
}
