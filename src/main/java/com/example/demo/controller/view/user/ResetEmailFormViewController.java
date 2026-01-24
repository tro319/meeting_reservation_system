package com.example.demo.controller.view.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.form.ResetEmailForm;

import lombok.RequiredArgsConstructor;

/*
 * パスワードリセットメールアドレス入力フォーム表示群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class ResetEmailFormViewController {
	
	/*
	 * リセットemail入力フォーム画面表示
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイル
	 * 
	 */
	
	@GetMapping("/user/reset_email")
	public String formView(HttpSession session, Model model) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId != null) {
			
			return "redirect:/user/interviewers";
			
		} else {
			
			String inputResult = (String)session.getAttribute("input_result");
			
			ResetEmailForm emailForm = new ResetEmailForm();
			
			model.addAttribute("input_result", inputResult);
		
			
			model.addAttribute("reset_input_data", emailForm);
			
			return "user/reset_email";
			
			
		}
	}
	

}
