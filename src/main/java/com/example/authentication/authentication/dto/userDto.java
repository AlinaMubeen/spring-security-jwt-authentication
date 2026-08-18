package com.example.authentication.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class userDto {
	Integer id;
	@NotBlank(message="username must be provided")
	private String username;
	@NotBlank(message="please enter your password")
	private String password;
	@NotBlank(message="role must be user or admin")
	private String role;
}
