package com.example.demo.controller.view.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.form.RepassForm;

import lombok.RequiredArgsConstructor;

/*
 * パスワードリセットフォーム表示群
 * 
 * @author ys
 * 
 */

@Controller
@RequiredArgsConstructor

public class PassResetViewController {
	
	/*
	 * パスワードリセットフォーム画面表示
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイル
	 * 
	 */
	
	@GetMapping("/user/pass_reset_view")
	public String formView(HttpSession session, Model model) {
		
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId != null) {
			
			return "redirect:/user/interviewers";
			
		} else {
			
			String repassResult = (String)session.getAttribute("repass_result");
			
			if (repassResult != null) {
				
				model.addAttribute("repass_result", repassResult);
				
				session.removeAttribute("repass_result");
				
			}
			

			
			RepassForm repassForm = new RepassForm();
			
			model.addAttribute("repass_input_data", repassForm);
			
			return "user/reset_pass";
			
			
		}
	}
	

}
