package com.example.demo.controller.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.User;
import com.example.demo.model.form.LoginForm;
import com.example.demo.service.user.LoginService;

import lombok.RequiredArgsConstructor;

/*
 * ユーザーログイン処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class LoginController {
	
	private final LoginService service;
	
	private final PasswordEncoder passEncoder;
	
	/*
	 * ユーザーログイン処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form ユーザーログインフォーム入力情報
	 * @return 成功時 予約空き状況表示へ | 失敗時 入力フォームに戻す
	 * 
	 */
	
	@PostMapping("/user/login")
	public String login(HttpSession session, RedirectAttributes redirectAttributes, LoginForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログインしているかどうか
		
		if (loginId != null) {
			
			return "redirect:/user/reservation_ables_get";
			
		}
		
		session.setAttribute("user_login_data", form);
		
		String email = form.getEmail();
		
		User userInfo = service.getUser(email);
		
		// 取得したユーザー情報が存在するか
		
		if (userInfo != null) {
			
			Boolean loginCheck = passEncoder.matches(form.getPass(), userInfo.getPass());
			
			if (loginCheck == false ) {
				
				session.setAttribute("login_result",  "パスワードが間違っています");
				
				return "redirect:/user/login";
				
			}
			
			session.setAttribute("login_result",  "ログインしました");
			
			session.setAttribute("log_name",  userInfo.getName());
			
			session.setAttribute("log_user_id",  userInfo.getId());
			
			session.removeAttribute("user_login_data");
			
			return "redirect:/user/reservations";
			
			
			
		} else {
			
			session.setAttribute("login_result",  "メールアドレスが間違っています");
			
			return "redirect:/user/login";
			
		}
		
	}
	

}
