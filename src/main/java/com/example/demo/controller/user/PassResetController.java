package com.example.demo.controller.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.entity.User;
import com.example.demo.model.form.RepassForm;
import com.example.demo.model.form.ResetEmailForm;
import com.example.demo.service.MailSendService;
import com.example.demo.service.PasswordResetService;
import com.example.demo.service.user.LoginService;
import com.example.demo.service.user.UserSetService;

import lombok.RequiredArgsConstructor;

/*
 * パスワードリセット処理群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class PassResetController {
	
	private final LoginService loginService;
	
	private final UserSetService userSetService;
	
	private final PasswordResetService resetService;
	
	private final MailSendService mailService;
	
	/*
	 * パスワードリセットメール送信処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form email入力情報
	 * @return ログインページ表示へ
	 * 
	 */
	
	@PostMapping("/user/pass_reset_send")
	public String sendEmail(HttpSession session, RedirectAttributes redirectAttributes, ResetEmailForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId != null) {
			
			return "redirect:/user/interviewers";
			
		}
		
		User userInfo = loginService.getUser(form.getEmail());
		
		if (userInfo != null) {
			
			resetService.sendResetEmail(form.getEmail());
			
			session.setAttribute("input_result", "パスワードリセットメールを、送信しました。");
			
		} else {
			
			session.setAttribute("input_result", "指定されたメールアドレスは、登録されていません。");
			
			return "redirect:/user/reset_email";
			
			
		}
		
		
		return "redirect:/user/login";
		
		
	}
	
	
	/*
	 * パスワードリセットフォーム表示前照合処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param email 指定されたメールアドレス
	 * @param token 指定されたトークン
	 * @return パスワードリセットフォーム表示へ
	 * 
	 */
	
	@GetMapping("/user/pass_reset")
	public String checkToken(HttpSession session, RedirectAttributes redirectAttributes, @RequestParam(required=false) String email, @RequestParam(required=false) String token) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId != null) {
			
			return "redirect:/user/interviewers";
			
		}
		
		if (email == null || token ==null) {
			
			return "redirect/user/login";
			 
		}
		
		Boolean checkToken = resetService.validateToken(email, token);
		
		if (checkToken == false) {
			
			return "redirect/user/login";
			
		}
		
		session.setAttribute("repass_token", token);
		
		session.setAttribute("repass_email", email);
		
		return "redirect:/user/pass_reset_view";
		
		
	}
	
	
	/*
	 * パスワードリセット処理
	 * 
	 * @param session セッション値情報
	 * @param redirectAttributes リダイレクト値情報
	 * @param form パスワードリセットフォーム入力情報
	 * @return ログインフォーム表示へ
	 * 
	 */
	
	@PostMapping("/user/pass_reset")
	public String setPass(HttpSession session, RedirectAttributes redirectAttributes, RepassForm form) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId != null) {
			
			return "redirect:/user/interviewers";
			
		}
		
		String email = (String)session.getAttribute("repass_email");
		
		String token = (String)session.getAttribute("repass_token");
		
		
		if (email != null && token != null) {
			
			
			if (!form.getPass().equals(form.getRepass())) {
				
				session.setAttribute("repass_result", "パスワード再入力が一致しません。");
				
				return "redirect:/user/pass_reset?email=" + email + "&token=" + token;
				
			}
			
			if (form.getPass() != null) {
				
				User userInfo = userSetService.setUserByEmail(email, form.getPass());
				
				resetService.removeToken(email);
				
			}
			
			session.removeAttribute("repass_email");
			
			session.removeAttribute("repass_token");
			
		}
		
		String subject = "パスワード再設定が完了しました! | 面談予約システム";
		
		String sendText = "いつも当サービス「面談予約システム」をご利用頂きありがとうございます。\nパスワードの再設定が完了しました。";
		
		mailService.sendMail(email,subject, sendText);
		
		session.setAttribute("input_result", "パスワード再設定が完了しました。");
		
		return "redirect:/user/login";
		
		
	}

}
