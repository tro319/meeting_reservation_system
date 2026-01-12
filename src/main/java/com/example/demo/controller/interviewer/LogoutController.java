package com.example.demo.controller.interviewer;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

/*
 * 実施者ログアウト処理群
 * 
 * @author ys
 * 
 */

@Controller("interviewerLogoutController")
@RequiredArgsConstructor

public class LogoutController {
	
	/*
	 * 実施者ログアウト処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return ログインページ表示へ
	 * 
	 */
	
	@GetMapping("/interviewer/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_interviewer_id");
		
		if (loginId == null) {
			
			return "redirect:/interviewer/login";
			
		}
		
		session.removeAttribute("log_interviewer_id");
		
		return "redirect:/interviewer/login";
		
		
	}

}
