package com.example.authentication.authentication.UserController;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
@GetMapping("/home")
public String home() {
	return "home";
}
@GetMapping("/user/home")
public String UserHome() {
	return "userhome";
}

@GetMapping("/admin/home")
public String AdminHome() {
	return "adminhome";
}


}
