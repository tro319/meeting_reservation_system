package com.example.demo.controller.manager;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Manager;
import com.example.demo.model.form.LoginForm;
import com.example.demo.service.manager.LoginService;

import lombok.RequiredArgsConstructor;

/*
 * 管理者ログイン処理群
 * 
 * @author ys
 * 
 */

@Controller("managerLoginController")
@RequiredArgsConstructor

public class LoginController {
	
	private final LoginService service;
	
	private final PasswordEncoder passEncoder;
	
	/*
	 * 管理者ログイン処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form 管理者ログインフォーム入力情報
	 * @return 成功時 実施者一覧取得へ | 失敗時 入力フォームに戻す
	 * 
	 */
	
	@PostMapping("/manager/login")
	public String login(HttpSession session, RedirectAttributes redirectAttributes, LoginForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		// ログインしているかどうか
		
		if (loginId != null) {
			
			return "redirect:/manager/interviewers";
			
		}
		
		session.setAttribute("manager_login_data", form);
		
		String email = form.getEmail();
		
		Manager managerInfo = service.getManager(email);
		
		// 取得した実施者情報が存在するか
		
		if (managerInfo != null) {
			
			Boolean loginCheck = passEncoder.matches(form.getPass(), managerInfo.getPass());
			
			if (loginCheck == false ) {
				
				session.setAttribute("login_result",  "パスワードが間違っています");
				
				return "redirect:/manager/login";
				
			}
			
			session.setAttribute("login_result",  "ログインしました");
			
			session.setAttribute("log_name",  managerInfo.getName());
			
			session.setAttribute("log_manager_id",  managerInfo.getId());
			
			session.removeAttribute("manager_login_data");
			
			return "redirect:/manager/interviewers";
			
			
			
			
		} else {
			
			session.setAttribute("login_result",  "メールアドレスが間違っています");
			
			return "redirect:/manager/login";
			
			
		}
		
		
	}
	

}
