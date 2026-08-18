package com.example.authentication.authentication.userService;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.authentication.authentication.model.User;
import com.example.authentication.authentication.repository.userRepository;
@Service
public class MyUserDetailService implements UserDetailsService{
   @Autowired
	userRepository repository;
   @Autowired
   PasswordEncoder encoder;

   @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=repository.getUserByUsername(username);
		if(user.isPresent()) {
			var obj=user.get();
			return 
					org.springframework.security.core.userdetails.User.builder().username(obj.getUsername()).password(obj.getPassword()).roles(obj.getRole()).build();
		}
		
		else {
			 throw new UsernameNotFoundException(username);
		}
	}
	public void addUserToRepository(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repository.saveAndFlush(user);
	}


	

}
