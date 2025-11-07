package com.example.demo.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	 * @return テンプレートファイルのパス
	 * 
	 * 
	 */
	
	@GetMapping("/users/signup/confirm")
	public String signupConfirmView(Model model, SignUpFormInfo form) {

		return "users/signup_confirm";
		
	}
	
	
	/**
	 * 
	 * 
	 * 会員登録修正ボタン遷移処理
	 * 
	 * @param model モデル
	 * @param redirectAttributes リダイレクト時値保持用
	 * @param form 入力情報
	 * 
	 */
	

	
	
	@GetMapping("/users/signup/return")
	
	public String signUpReturn(Model model, RedirectAttributes redirectAttributes, SignUpFormInfo form) {
		
		
		redirectAttributes.addFlashAttribute("signUpFormInfo", form);
		return "redirect:/users/signup";
		
	}
	
}
