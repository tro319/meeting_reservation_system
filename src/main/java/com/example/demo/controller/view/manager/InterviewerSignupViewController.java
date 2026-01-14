package com.example.demo.controller.view.manager;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.form.manager.SignupForm;

import lombok.RequiredArgsConstructor;

/*
 * 実施者登録処理表示群
 * 
 * @author ys
 * 
 */

@Controller("managerInterviewerSignupViewController")
@RequiredArgsConstructor

public class InterviewerSignupViewController {
	
	/*
	 * 実施者登録フォーム画面表示
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイル
	 * 
	 */
	
	@GetMapping("/manager/interviewer_signup")
	public String formView(HttpSession session, Model model) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		} else {
			
			String errMsg = (String)session.getAttribute("signup_err_msg");
			
			model.addAttribute("signup_err_msg", errMsg);
			
			SignupForm signupForm = (SignupForm)session.getAttribute("interviewer_signup_data");
			
			if (signupForm == null) {
				
				signupForm = new SignupForm();
				
				
			}
			
			model.addAttribute("interviewer_signup_data", signupForm);
			
			return "manager/interviewer_signup";
			
		}
		 
	}
	
	
	/*
	 * 実施者登録確認画面表示
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイル
	 * 
	 */
	
	@GetMapping("/manager/interviewer_signup/confirm")
	public String confirmView(HttpSession session, Model model) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		} else {
			
			SignupForm signupForm = (SignupForm)session.getAttribute("interviewer_signup_data");
			
			if (signupForm == null) {
				
				return "redirect:/manager/interviewer_signup";
				
			}
		
			model.addAttribute("interviewer_signup_data", signupForm);
			
			return "manager/interviewer_signup_confirm";
			
		}
		 
	}
	

}
