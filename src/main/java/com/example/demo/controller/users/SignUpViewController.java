package com.example.demo.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.users.SignUpFormInfo;

@Controller
public class SignUpViewController {
	
	
	/**
	 * 
	 * 
	 * 会員登録画面表示
	 * 
	 * @param model モデル
	 * @return テンプレートファイルのパス
	 * 
	 * 
	 */
	
	@GetMapping("/users/signup")
	public String signUpView(Model model, SignUpFormInfo form) {
		
		return "users/signup";
		
	}
	
	
	/**
	 * 
	 * 
	 * 会員登録確認画面表示
	 * 
	 * @return テンプレートファイルのパス
	 * 
	 * 
	 */
	
	@GetMapping("/users/signup/confirm")
	public String signupConfirmView() {

		return "users/signup_confirm";
		
	}
	
}
