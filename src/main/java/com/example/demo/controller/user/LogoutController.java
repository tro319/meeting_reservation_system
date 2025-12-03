package com.example.demo.controller.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

/*
 * ユーザーログアウト処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class LogoutController {
	
	/*
	 * ユーザーログアウト処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return ログインページ表示へ
	 * 
	 */
	
	@GetMapping("/user/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		session.removeAttribute("log_user_id");
		
		return "redirect:/user/login";
		
		
	}

}
