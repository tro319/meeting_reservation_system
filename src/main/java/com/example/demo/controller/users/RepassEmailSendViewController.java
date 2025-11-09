package com.example.demo.controller.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.users.RepassEmailFormInfo;

import lombok.RequiredArgsConstructor;

/**
 * パスワードリセットURL送信されるメールアドレス入力画面表示
 * 
 * @author ys
 * 
 * 
 */

@Controller
@RequiredArgsConstructor
public class RepassEmailSendViewController {
	
	
	/**
	 * 
	 * 
	 * パスワードリセットurl送信用メールアドレス入力フォーム画面表示
	 * 
	 * @param model モデル
	 * @param form パスワードリセットurl送信用メールアドレス入力フォーム入力情報
	 * @return テンプレートファイルへのパス
	 * 
	 * 
	 */
	
	@GetMapping("/users/repass_email")
	public String repassEmailFormView(Model model, RepassEmailFormInfo form) {
		
		String repassEmailErr1 = (String)model.getAttribute("repassEmailErr1");
		
		if (repassEmailErr1 != null) {
			
			model.addAttribute("repassEmailErr1", repassEmailErr1);
			
		}
		
		return "users/repass_email_form";

	}
	
	
	

}
