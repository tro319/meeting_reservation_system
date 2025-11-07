package com.example.demo.controller.users;

import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.kyoutu.AppUtil;
import com.example.demo.kyoutu.ErrMessageConst;
import com.example.demo.model.common.LoginFormInfo;
import com.example.demo.model.users.UserInfo;
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
	
	private final MessageSource messageSource;
	
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
	 * 
	 * 
	 * ログイン処理
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 認証失敗時 そのままの遷移パス | 成功時 メニュー画面への遷移パス
	 * 
	 * 
	 */
	
	
	
	@PostMapping("/users/login")
	public String loginResult(Model model, HttpSession session, LoginFormInfo form) {
		
		
		var userInfoLoginCheck = service.searchRepositoryByEmail(form.getEmail());
		
		Boolean loginCheck = userInfoLoginCheck.isPresent() && passwordEncoder.matches(form.getPass(), userInfoLoginCheck.get().getPass());
		
		System.out.println(passwordEncoder.encode(form.getPass()));
		
		
		if (loginCheck == true) {
			
			int loginResultId = userInfoLoginCheck.get().getId();
			session.setAttribute("userLoginId", loginResultId);
			return "redirect:/users/menu";
			
			
		} else {
			
			var errMsg = AppUtil.getMessage(messageSource,  ErrMessageConst.LOGIN_ERR_INPUT);
			model.addAttribute("errMsg", errMsg);
			return "redirect:/users/login";
			
		}
	}
	
	/**
	 * 
	 * 
	 * ログイン処理
	 * 
	 * @param model モデル
	 * @param session セッション情報
	 * @return 未ログイン時 ログインページへ | 成功時 ユーザー情報取得画面への遷移パス
	 * 
	 * 
	 */
	
	
	
	@GetMapping("/users/user_info")
	public String getUserInfo(Model model, HttpSession session) {
		
		
		Integer presentUserId = null;
		
		
		
		presentUserId = (Integer)session.getAttribute("userLoginId");
		
		
		if (presentUserId != null) {
			
			Optional<UserInfo> userGetResult = service.searchRepositoryById(presentUserId);
			
			session.setAttribute("userGetResult", userGetResult.get());
			return "redirect:/users/user_result";
			
		} else {
			
			
			return "redirect:/users/login";
			
		}
		

		

	}
	
}
