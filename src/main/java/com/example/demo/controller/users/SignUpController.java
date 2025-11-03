package com.example.demo.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.users.SignUpFormInfo;
import com.example.demo.service.users.SignUpService;

import lombok.RequiredArgsConstructor;


/**
 * 会員登録関連ページを繋げる処理
 * 
 *@author ys 
 * 
 */

@Controller
@RequiredArgsConstructor
public class SignUpController {
	
	// インスタンス変数定義
	
	private final SignUpService service;

	
	
	// TODO フッター共通設定追加 (layout.html、各htmlに) tamapon
	

	
	
	/**
	 * 
	 * 
	 * 会員登録確認処理
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * 
	 */
	
	@PostMapping("/users/signup/confirm")
	
	public String signUpConfirm(Model model, SignUpFormInfo form) {
		
		Boolean checkResult = false;
		
		checkResult = service.confirm(form.getEmail(), form.getUserName());
		
		if (checkResult == true) {
			
			return "redirect:/users/signup";
			
		}
		
		System.out.println(checkResult);
		
		return "redirect:/users/signup/confirm";
		
	}
	
	
	
	
	/**
	 * 
	 * 
	 * 会員登録処理
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * 
	 */
	
	
	// TODO メールアドレス重複チェック ys
	
	// TODO パスワード再入力、整合性チェック ys
	
	
	@PostMapping("/users/signup")
	
	public void signUpResult(Model model, SignUpFormInfo form) {
		
		service.register(form);
		
	}
	
}
