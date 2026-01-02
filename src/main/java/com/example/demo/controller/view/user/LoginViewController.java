package com.example.demo.controller.view.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.form.LoginForm;

import lombok.RequiredArgsConstructor;

/*
 * ユーザーログイン表示群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class LoginViewController {
	
	/*
	 * ユーザーログインフォーム画面表示
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @param form ユーザーログインフォーム入力情報
	 * @return 対象テンプレートファイル
	 * 
	 */
	
	@GetMapping("/user/login")
	public String formView(HttpSession session, Model model, LoginForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId != null) {
			
			return "redirect:/user/reservation_ables_get";
			
		} else {
			
			String loginResult = (String)session.getAttribute("login_result");
			
			model.addAttribute("login_result", loginResult);
			
			LoginForm loginForm = (LoginForm)session.getAttribute("user_login_data");
			
			if (loginForm == null) {
				
				loginForm = new LoginForm();
				
			}
			
			model.addAttribute("user_login_data", loginForm);
			
			return "user/login";
			
			
		}
	}
	

}
