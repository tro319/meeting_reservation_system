package com.example.demo.controller.manager;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.Interviewer;
import com.example.demo.model.form.manager.SignupForm;
import com.example.demo.service.MailSendService;
import com.example.demo.service.manager.InterviewerSignupService;

import lombok.RequiredArgsConstructor;

/*
 * 実施者登録処理群
 * 
 * @author ys
 * 
 */

@Controller("managerInterviewerSignupController")
@RequiredArgsConstructor

public class InterviewerSignupController {
	
	private final InterviewerSignupService service;
	
	private final MailSendService mailService;
	
	/*
	 * 実施者情報確認処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form 実施者登録フォーム入力情報
	 * @return 成功時 確認ページ表示へ | 失敗時 入力フォームに戻す
	 * 
	 */
	
	@PostMapping("/manager/interviewer_signup/confirm")
	public String confirm(HttpSession session, RedirectAttributes redirectAttributes, SignupForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		} else {
			
			session.setAttribute("interviewer_signup_data", form);
			
			Boolean doubleCheck = service.checkDouble(form);
			
			// 登録情報が重複しているか
			
			if (doubleCheck) {
				
				session.setAttribute("signup_err_msg", "実施者名 または メールアドレスが既に登録されています。");
				
				return "redirect:/manager/interviewer_signup/return";
				
			} else {
				
				String pass = form.getPass();
				
				String repass = form.getRepass();
				
				// パスワード再入力が一致しているか
				
				if (!pass.equals(repass)) {
					
					session.setAttribute("signup_err_msg", "パスワード入力が一致していません。");
					
					return "redirect:/manager/interviewer_signup/return";
					
				}
				
				
				return "redirect:/manager/interviewer_signup/confirm";
				
			}
			
			
			
		}
		
		
		
	}
	
	
	/*
	 * 実施者登録フォームに戻る処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @return 入力フォームに戻る
	 * 
	 */
	
	@GetMapping("/manager/interviewer_signup/return")
	public String back(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		} else {
			
			
			SignupForm returnForm = (SignupForm)session.getAttribute("interviewer_signup_data");
			
			redirectAttributes.addFlashAttribute("interviewer_signup_data", returnForm);
			
			
			return "redirect:/manager/interviewer_signup";
			
		}
		
	}
	
	
	
	/*
	 * 実施者登録処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form 実施者登録フォーム入力情報
	 * @return 成功時 実施者情報取得へ | 失敗時 登録フォームへ戻る
	 * 
	 */
	
	@PostMapping("/manager/interviewer_signup")
	public String register(HttpSession session, RedirectAttributes redirectAttributes, SignupForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		// ログインしているかどうか
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		} else {
			
			Boolean doubleCheck = service.checkDouble(form);
			
			if (doubleCheck) {
				
				session.setAttribute("interviewer_signup_data", form);
				
				session.setAttribute("signup_err_msg", "実施者名 または メールアドレスが、既に登録されています。");
				
				return "redirect:/manager/interviewer_signup/return";
				
			}
			
			Interviewer signupResult = service.register(form, loginId);
			
			
			String signupName = signupResult.getName();
			
			String signupEmail = signupResult.getEmail();
			
//			String signupSendText = signupName + "さん、" + "当サービス、「面談予約システム」への登録ありがとうございます! \n" + "引き続きよろしくお願い致します。";
//			
//			mailService.sendMail(signupEmail, "会員登録が完了しました! | 面談予約システム", signupSendText);
			
			session.removeAttribute("interviewer_signup_data");
			
			int interviewerId = signupResult.getId();
			
			
			session.setAttribute("signup_result", "実施者登録が完了しました!");
			
			
			return "redirect:/manager/interviewer_get?id=" + interviewerId;
			
			
		}
	
	}
	

}
