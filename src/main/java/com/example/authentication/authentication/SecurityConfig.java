package com.example.authentication.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.authentication.authentication.userService.MyUserDetailService;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
@Configuration
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security, jwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
		return security
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->auth
						.requestMatchers("/home","/register","/auth/login").permitAll()
						.requestMatchers("/admin/**").hasRole("admin")
						.requestMatchers("/user/**").hasRole("user")
						.anyRequest().authenticated())
				.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
				
				.build();
	}
@Bean
public AuthenticationProvider authenticationProvider(MyUserDetailService myUserDetailService) {
	DaoAuthenticationProvider provider = new DaoAuthenticationProvider(myUserDetailService);
	provider.setPasswordEncoder(passwordEncoder());

	return provider;
}
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
	return config.getAuthenticationManager();
}

}
