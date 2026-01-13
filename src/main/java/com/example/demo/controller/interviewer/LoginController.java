package com.example.demo.controller.interviewer;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.model.form.LoginForm;
import com.example.demo.service.interviewer.LoginService;

import lombok.RequiredArgsConstructor;

/*
 * 実施者ログイン処理群
 * 
 * @author ys
 * 
 */

@Controller("interviewerLoginController")
@RequiredArgsConstructor

public class LoginController {
	
	private final LoginService service;
	
	private final PasswordEncoder passEncoder;
	
	/*
	 * 実施者ログイン処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form 実施者ログインフォーム入力情報
	 * @return 成功時 予約一覧取得へ | 失敗時 入力フォームに戻す
	 * 
	 */
	
	@PostMapping("/interviewer/login")
	public String login(HttpSession session, RedirectAttributes redirectAttributes, LoginForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_interviewer_id");
		
		// ログインしているかどうか
		
		if (loginId != null) {
			
			return "redirect:/interviewer/reservations";
			
		}
		
		session.setAttribute("interviewer_login_data", form);
		
		String email = form.getEmail();
		
		Interviewer interviewerInfo = service.getInterviewer(email);
		
		// 取得した実施者情報が存在するか
		
		if (interviewerInfo != null) {
			
			Boolean loginCheck = passEncoder.matches(form.getPass(), interviewerInfo.getPass());
			
			if (loginCheck == false ) {
				
				session.setAttribute("login_result",  "パスワードが間違っています");
				
				return "redirect:/interviewer/login";
				
			}
			
			session.setAttribute("login_result",  "ログインしました");
			
			session.setAttribute("log_name",  interviewerInfo.getName());
			
			session.setAttribute("log_interviewer_id",  interviewerInfo.getId());
			
			session.removeAttribute("interviewer_login_data");
			
			return "redirect:/interviewer/reservations";
			
			
			
			
		} else {
			
			session.setAttribute("login_result",  "メールアドレスが間違っています");
			
			System.out.println(passEncoder.encode(form.getPass()));
			
			return "redirect:/interviewer/login";
			
			
		}
		
		
	}
	

}
