package com.example.demo.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.common.LoginFormInfo;

@Controller
public class LoginController {
	
	@GetMapping("/users/login")
	public String loginView(Model model, LoginFormInfo form) {
		
		return "users/login";
		
	}
	
	
	@PostMapping("/users/login")
	
	public void loginResult(LoginFormInfo form) {
		
		System.out.println(form.toString());
	}
	
}
