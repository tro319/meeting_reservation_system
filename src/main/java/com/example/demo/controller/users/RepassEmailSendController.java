package com.example.demo.controller.users;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.users.RepassEmailFormInfo;
import com.example.demo.service.common.EmailService;
import com.example.demo.service.users.LoginService;
import com.example.demo.service.users.SignUpService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RepassEmailSendController {
	
	// インスタンス定義
	
	private final EmailService emailService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final SignUpService signUpService;
	
	private final LoginService loginService;
	
	@GetMapping("/users/repass_email")
	public String repassEmailFormView(Model model, RepassEmailFormInfo form) {
		
		String repassEmailErr1 = (String)model.getAttribute("repassEmailErr1");
		
		if (repassEmailErr1 != null) {
			
			model.addAttribute("repassEmailErr1", repassEmailErr1);
			
		}
		
		return "users/repass_email_form";

	}
	
	
	@PostMapping("/users/repass_email")
	public String sendRepassEmail(Model model, RedirectAttributes redirectAttributes, HttpSession session, RepassEmailFormInfo form)  {
		
		String toEmail = form.getEmail();
		
		Boolean emailCheck = signUpService.repassCheckEmail(toEmail);
		
		if (emailCheck == true) {
			
			String repassUserName = loginService.searchRepositoryByEmail(toEmail).get().getUserName();
			
			String subject = "パスワード再設定のご案内 | 面談予約システム";
			
			String encodedHash = passwordEncoder.encode(toEmail);
			
			// セッションにハッシュ値セット (後にurl末尾パラメーターでの判断で使うため)
			
			session.setAttribute("repassURLHash", encodedHash);
			
			
			String url = "http://localhost:8080/users/signup/repass?set=" + encodedHash;
			
			String text = "ユーザーネーム: " + repassUserName + " さん \n いつも、面談予約システムをご利用頂き、ありがとうございます! \n 下記が、パスワード再設定用のurlです。\n" + url;
			
			emailService.sendEmail(toEmail, subject, text);
			
			redirectAttributes.addFlashAttribute("repassEmailSucc1", "指定されたメールアドレスに、送信しました" );
			
			return "redirect:/users/login";
			
		}
		
		redirectAttributes.addFlashAttribute("repassEmailErr1", "指定されたメールアドレスは、登録されていません" );
		
		return "redirect:/users/repass_email";
		
		

		
	}


}
