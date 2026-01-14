package com.example.demo.controller.manager;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

/*
 * 管理者ログアウト処理群
 * 
 * @author ys
 * 
 */

@Controller("managerLogoutController")
@RequiredArgsConstructor

public class LogoutController {
	
	/*
	 * 管理者ログアウト処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return ログインページ表示へ
	 * 
	 */
	
	@GetMapping("/manager/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		session.removeAttribute("login_result");
		
		session.removeAttribute("log_manager_id");
		
		return "redirect:/manager/login";
		
		
	}

}
