package com.example.demo.controller.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.user.UserDeleteService;

import lombok.RequiredArgsConstructor;

/*
 * ユーザー削除処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class UserDeleteController {
	
	private final UserDeleteService service;
	
	/*
	 * ユーザー削除処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return ログインフォームへ
	 * 
	 */
	
	@GetMapping("/user/user_delete")
	public String deleteUser(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログインしていなければ
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		
		service.deleteUserById(loginId);
		
		session.removeAttribute("log_user_id");
		
		redirectAttributes.addFlashAttribute("退会が完了しました!");
		
		return "redirect:/user/login";
		
	}

}
