package com.example.demo.controller.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.User;
import com.example.demo.model.form.user.SignupForm;
import com.example.demo.service.MailSendService;
import com.example.demo.service.user.SignupService;

import lombok.RequiredArgsConstructor;

/*
 * ユーザー登録処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class SignupController {
	
	private final SignupService service;
	
	private final MailSendService mailService;
	
	/*
	 * ユーザー情報確認処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form ユーザー登録フォーム入力情報
	 * @return 成功時 確認ページ表示へ | 失敗時 入力フォームに戻す
	 * 
	 */
	
	@PostMapping("/user/signup/confirm")
	public String confirm(HttpSession session, RedirectAttributes redirectAttributes, SignupForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログイン済みかどうか
		
		if (loginId != null) {
			
			return "redirect:/user/reservation_ables_get";
			
		} else {
			
			session.setAttribute("user_signup_data", form);
			
			Boolean doubleCheck = service.checkDouble(form);
			
			// 登録情報が重複しているか
			
			if (doubleCheck) {
				
				session.setAttribute("signup_err_msg", "ユーザーネーム または メールアドレスが既に登録されています。");
				
				return "redirect:/user/signup/return";
				
			} else {
				
				String pass = form.getPass();
				
				String repass = form.getRepass();
				
				// パスワード再入力が一致しているか
				
				if (!pass.equals(repass)) {
					
					session.setAttribute("signup_err_msg", "パスワード入力が一致していません。");
					
					return "redirect:/user/signup/return";
					
				}
				
				
				
			}
			
			return "redirect:/user/signup/confirm";
			
		}
		
		
		
	}
	
	
	/*
	 * ユーザー登録フォームに戻る処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param model モデル値情報
	 * @return 入力フォームに戻る
	 * 
	 */
	
	@GetMapping("/user/signup/return")
	public String back(HttpSession session, RedirectAttributes redirectAttributes) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログイン済みかどうか
		
		if (loginId != null) {
			
			return "redirect:/user/reservation_ables_get";
			
		} else {
			
			
			SignupForm returnForm = (SignupForm)session.getAttribute("user_signup_data");
			
			redirectAttributes.addFlashAttribute("user_signup_data", returnForm);
			
			
			return "redirect:/user/signup";
		}
		
	}
	
	
	
	/*
	 * ユーザー登録処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form ユーザー登録フォーム入力情報
	 * @return 成功時 ユーザー情報取得へ | 失敗時 登録フォームへ戻る
	 * 
	 */
	
	@PostMapping("/user/signup")
	public String register(HttpSession session, RedirectAttributes redirectAttributes, SignupForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		// ログイン済みかどうか
		
		if (loginId != null) {
			
			return "redirect:/user/reservation_ables_get";
			
		} else {
			
			Boolean doubleCheck = service.checkDouble(form);
			
			if (doubleCheck) {
				
				session.setAttribute("user_signup_data", form);
				
				session.setAttribute("signup_err_msg", "ユーザーネーム または メールアドレスが、既に登録されています。");
				
				return "redirect:/user/signup/return";
				
			}
			
			User signupResult = service.register(form);
			
			int signupId = signupResult.getId();
			
			String signupName = signupResult.getName();
			
			String signupEmail = signupResult.getEmail();
			
			String signupSendText = signupName + "さん、" + "当サービス、「面談予約システム」への登録ありがとうございます! \n" + "引き続きよろしくお願い致します。";
			
			mailService.sendMail(signupEmail, "会員登録が完了しました! | 面談予約システム", signupSendText);
			
			session.removeAttribute("user_signup_data");
			
			session.setAttribute("log_user_id", signupId);
			
			session.setAttribute("signup_user", signupResult);
			
			session.setAttribute("signup_result", "ユーザー登録が完了しました!");
			
			
			return "redirect:/user/user_get";
			
			
		}
	
	}
	

}
