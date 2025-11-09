package com.example.demo.controller.users;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.users.RepassEmailFormInfo;
import com.example.demo.model.users.UserInfo;
import com.example.demo.service.common.EmailService;
import com.example.demo.service.users.LoginService;
import com.example.demo.service.users.SignUpService;

import lombok.RequiredArgsConstructor;

/**
 * パスワードリセットURLメール送信処理
 * 
 * @author ys
 * 
 * 
 */

@Controller
@RequiredArgsConstructor
public class RepassEmailSendController {
	
	// インスタンス定義
	
	private final EmailService emailService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final SignUpService signUpService;
	
	private final LoginService loginService;

	
	
	/**
	 * 
	 * 
	 * 入力されたメールアドレスが登録されいたら、パスワードリセット用メール送信する処理
	 * 
	 * @param model モデル
	 * @param redirectAttributes リダイレクト時値保持用
	 * @param session セッション情報
	 * @param form パスワードリセットurl送信用メールアドレス入力フォーム入力情報
	 * @return メールアドレスが存在していれば ログインページへの遷移パス | 存在していなければ 現在のメールアドレス入力フォームに維持
	 * 
	 * 
	 */
	
	
	@PostMapping("/users/repass_email")
	public String sendRepassEmail(Model model, RedirectAttributes redirectAttributes, HttpSession session, RepassEmailFormInfo form)  {
		
		String toEmail = form.getEmail();
		
		UserInfo gotUser = loginService.searchRepositoryByEmail(toEmail).get();
		
		Boolean emailCheck = signUpService.repassCheckEmail(toEmail);
		
		if (emailCheck == true) {
			
			String repassUserName = gotUser.getUserName();
			
			int repassUserID = gotUser.getId();
			
			
			String subject = "パスワード再設定のご案内 | 面談予約システム";
			
			String encodedHash = passwordEncoder.encode(toEmail);
			
			// セッションにハッシュ値セット (後にurl末尾パラメーターでの判断で使うため)
			
			session.setAttribute("repassURLHash", encodedHash);
			
			// セッションに対象ユーザーIDセット
			
			session.setAttribute("repassUserID", repassUserID);
			
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
