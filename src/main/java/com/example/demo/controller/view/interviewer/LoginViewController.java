package com.example.demo.controller.view.interviewer;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.form.LoginForm;

import lombok.RequiredArgsConstructor;

/*
 * 実施者ログイン表示群
 * 
 * @author ys
 * 
 */

@Controller("interviewerLoginViewController")
@RequiredArgsConstructor

public class LoginViewController {
	
	/*
	 * 実施者ログインフォーム画面表示
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイル
	 * 
	 */
	
	@GetMapping("/interviewer/login")
	public String formView(HttpSession session, Model model) {
		
		Integer loginId = (Integer)session.getAttribute("log_interviewer_id");
		
		if (loginId != null) {
			
			return "redirect:/interviewer/reservations";
			
		} else {
			
			String loginResult = (String)session.getAttribute("login_result");
			
			model.addAttribute("login_result", loginResult);
			
			LoginForm loginForm = (LoginForm)session.getAttribute("interviewer_login_data");
			
			if (loginForm == null) {
				
				loginForm = new LoginForm();
				
			}
			
			model.addAttribute("interviewer_login_data", loginForm);
			
			return "interviewer/login";
			
			
		}
	}
	

}
