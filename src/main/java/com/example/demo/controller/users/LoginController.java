package com.example.demo.controller.users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.common.LoginFormInfo;
import com.example.demo.service.users.LoginService;

import lombok.RequiredArgsConstructor;


/**
 * ログイン関連ページを繋げる処理
 * 
 *@author ys 
 * 
 */

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	// インスタンス変数定義
	
	private final LoginService service;
	
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * ログイン画面、初期表示
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 表示画面
	 * 
	 */
	
	@GetMapping("/users/login")
	public String loginView(Model model, LoginFormInfo form) {
		
		return "users/login";
		
	}
	
	/**
	 * ログイン処理
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 認証失敗時 そのままの遷移パス | 成功時 メニュー画面への遷移パス
	 */
	
	
	@PostMapping("/users/login")
	
	public String loginResult(Model model, LoginFormInfo form) {
		
		// TODO パスワードハッシュ化
		
		var userInfo = service.searchRepositoryByEmail(form.getEmail());
		
		Boolean loginCheck = userInfo.isPresent() && passwordEncoder.matches(form.getPass(), userInfo.get().getPass());
		
		// TODO エラーメッセージ、プロパティファイルで管理する
		
		if (loginCheck == true) {
			
			return "redirect:/users/menu";
			
		} else {
			
			model.addAttribute("err_msg", "ログイン情報が、間違っています。");
			return "/users/login";
			
		}
	}
	
}
