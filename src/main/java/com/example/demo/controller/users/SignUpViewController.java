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
	 * @param form 会員登録フォーム入力情報
	 * @return テンプレートファイルのパス
	 * 
	 * 
	 */
	
	@GetMapping("/users/signup")
	public String signUpView(Model model, SignUpFormInfo form) {
		
		if (form != null) {
			
			model.addAttribute("signUpFormInfo", form);
			
		}
		
		return "users/signup";
		
	}
	
	
	/**
	 * 
	 * 
	 * 会員登録確認画面表示
	 * 
	 * @param model モデル
	 * @param form 会員登録フォーム入力情報
	 * @return テンプレートファイルのパス
	 * 
	 * 
	 */
	
	@GetMapping("/users/signup/confirm")
	public String signupConfirmView(Model model, SignUpFormInfo form) {

		return "users/signup_confirm";
		
	}
	
	

}
