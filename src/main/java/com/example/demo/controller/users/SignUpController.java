package com.example.demo.controller.users;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.users.SignUpFormInfo;
import com.example.demo.model.users.UserInfo;
import com.example.demo.service.users.SignUpService;

import lombok.RequiredArgsConstructor;


/**
 * 会員登録関連ページを繋げる処理
 * 
 *@author ys 
 * 
 */

@Controller
@RequiredArgsConstructor
public class SignUpController {
	
	// インスタンス変数定義
	
	private final SignUpService service;

	
	
	// TODO フッター共通設定追加 (layout.html、各htmlに) tamapon
	

	
	
	/**
	 * 
	 * 
	 * 会員登録確認処理
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * 
	 */
	
	@PostMapping("/users/signup/confirm")
	
	public String signUpConfirm(Model model, RedirectAttributes redirectAttributes, SignUpFormInfo form) {
		
		Boolean checkResult = null;
		
		String inputPass = form.getPass();
		
		String rePass = form.getRepass();
		
		if (inputPass.equals(rePass)) {
			
			checkResult = service.confirm(form.getEmail(), form.getUserName());
			
			if (checkResult == true) {
				
				redirectAttributes.addFlashAttribute("err1", "ユーザー名 または メールアドレスが既に登録されています");
				return "redirect:/users/signup";
				
			}
			
			redirectAttributes.addFlashAttribute("signUpFormInfo", form);
			return "redirect:/users/signup/confirm";
			
			
		}
		
		System.out.println(checkResult);
		
		redirectAttributes.addFlashAttribute("err2", "パスワード入力が一致しません");
		return "redirect:/users/signup";
		
		
		
		

		
	}
	
	
	
	
	/**
	 * 
	 * 
	 * 会員登録処理
	 * 
	 * @param model モデル
	 * @param session セッション
	 * @param redirectAttributes リダイレクト時値保持用
	 * @param form 入力情報
	 * 
	 */
	

	
	
	@PostMapping("/users/signup")
	
	public String signUpResult(Model model, HttpSession session, RedirectAttributes redirectAttributes, SignUpFormInfo form) {
		
		UserInfo signUpResult = service.register(form);
		
		if (signUpResult != null) {
			
			session.setAttribute("userLoginId", signUpResult.getId());
			return "redirect:/users/user_info";
			
		}
		
		redirectAttributes.addFlashAttribute("err3", "会員登録に失敗しました。");
		return "redirect:/users/signup";
		
	}
	
}
