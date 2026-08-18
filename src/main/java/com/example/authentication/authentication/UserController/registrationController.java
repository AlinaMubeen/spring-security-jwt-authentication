package com.example.authentication.authentication.UserController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.authentication.authentication.model.User;
import com.example.authentication.authentication.dto.userDto;
import com.example.authentication.authentication.repository.userRepository;
import com.example.authentication.authentication.userService.MyUserDetailService;

import jakarta.validation.Valid;

@RestController
public class registrationController {
	@Autowired 
	MyUserDetailService myUserDetailService;
	@PostMapping("/register")
	public ResponseEntity<?> RegisterUser(@Valid @RequestBody userDto userdto,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,String> errors=new HashMap<String,String>();
			for(FieldError error :result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		}
		else {
			 User user = new User();
			user.setUsername(userdto.getUsername());
			user.setPassword(userdto.getPassword());
			user.setRole(userdto.getRole());
			myUserDetailService.addUserToRepository(user);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("user added successfully");
	} 
}



