package com.example.demo.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	/**
	 * 会員登録画面、初期表示
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 * 
	 */
	
	@GetMapping("/users/signup")
	public String signUpView(Model model, SignUpFormInfo form) {
		
		return "users/signup";
		
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
	
	
	@PostMapping("/users/signup")
	
	public void signUpResult(Model model, SignUpFormInfo form) {
		
		service.register(form);
		
	}
	
}
