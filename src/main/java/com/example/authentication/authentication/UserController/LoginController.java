package com.example.authentication.authentication.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentication.authentication.model.User;
import com.example.authentication.authentication.model.UserLogin;
import com.example.authentication.authentication.userService.MyUserDetailService;
import com.example.authentication.authentication.userService.jwtService;

@RestController
@RequestMapping("/auth")
public class LoginController {
	@Autowired
	MyUserDetailService userDetails;
	@Autowired
	AuthenticationManager authManager;
	@Autowired
	jwtService jwts;
	
	@PostMapping("/login")
	public String loginManager(@RequestBody UserLogin request) {
		Authentication auth=authManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		if (auth.isAuthenticated()) {
			UserDetails user = (UserDetails) auth.getPrincipal();
			return jwts.generateToken(user);
		}
		return "fail";
	}

}
