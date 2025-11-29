package com.example.demo.controller.view.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.form.user.SignupForm;

import lombok.RequiredArgsConstructor;

/*
 * ユーザー登録処理表示群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class SignupViewController {
	
	/*
	 * ユーザー登録フォーム画面表示
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @param form ユーザー登録フォーム入力情報
	 * @return 対象テンプレートファイル
	 * 
	 */
	
	@GetMapping("/user/signup")
	public String formView(HttpSession session, Model model, SignupForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId != null) {
			
			return "redirect: /user/reservation_get";
			
		} else {
			
			SignupForm signupForm = (SignupForm)session.getAttribute("user_signup_data");
			
			if (signupForm != null) {
				
				model.addAttribute("signup_form", signupForm);
				
				String errMsg = (String)model.getAttribute("signup_err_msg");
				
				if (errMsg != null) {
					
					model.addAttribute("signup_err_msg", errMsg);
					
				}
				
				
			}
			
			return "user/signup";
			
		}
		 
	}
	
	
	/*
	 * ユーザー登録確認画面表示
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @param form ユーザー登録フォーム入力情報
	 * @return 対象テンプレートファイル
	 * 
	 */
	
	@GetMapping("/user/signup/confirm")
	public String confirmView(HttpSession session, Model model) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId != null) {
			
			return "redirect: /user/reservation_get";
			
		} else {
			
			SignupForm signupForm = (SignupForm)session.getAttribute("user_signup_data");
			
			if (signupForm == null) {
				
				return "redirect: /user/signup";
				
			}
			
			model.addAttribute("signup_form", signupForm);
			
			return "user/signup_confirm";
			
		}
		 
	}
	

}
