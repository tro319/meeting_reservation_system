package com.example.demo.controller.users;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.users.RepassFormInfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RepassController {

	@GetMapping("/users/signup/repass")
	public String repassFormView(@RequestParam String set, Model model, HttpSession session, RepassFormInfo form) {
		
		String checkHash = (String)session.getAttribute("repassURLHash");
		
		if (!checkHash.equals(set)) {
			
			System.out.println(set);
			System.out.println(checkHash);
			
			return "redirect:/users/login";
			
		}
		
		return "users/pass_update_form";
		
	}
	
	
}
