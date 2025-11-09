package com.example.demo.controller.users;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.users.UserInfo;
import com.example.demo.model.users.UserUpdateFormInfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserUpdateViewController {
	

	
	@GetMapping("/users/user_update")
	public String userUpdate(Model model, HttpSession session, UserUpdateFormInfo form) {
		
		
		UserInfo updateUserBefore = (UserInfo)session.getAttribute("userGetResult");
		
		String userUpdateResult = (String)model.getAttribute("updateResult");
		
		
		form.setUserName(updateUserBefore.getUserName());
		form.setEmail(updateUserBefore.getEmail());
		
		
		// 後の入力値比較用のための、既存値設定
		
		form.setHidId(updateUserBefore.getId());
		form.setHidUserName(updateUserBefore.getUserName());
		form.setHidEmail(updateUserBefore.getEmail());
		
		model.addAttribute("updateForm", form);
		
		if (userUpdateResult != null) {
			
			model.addAttribute("userUpdateResult", userUpdateResult);
			
			
		}
		
		return "users/user_update_form";
		
		
	}

	

	
	
}
