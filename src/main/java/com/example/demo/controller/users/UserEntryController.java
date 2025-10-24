package com.example.demo.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.common.UserEntryFormInfo;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserEntryController {
	
	
	@GetMapping("/users/user_entry")
	
	public String viewUserEntry(Model model, UserEntryFormInfo userEntryFormInfo) {
		
		return "users/user_entry";
				
	}

}
