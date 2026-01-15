package com.example.demo.controller.view.user;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

/*
 * 実施者一覧取得結果表示処理群
 * 
 * @author ys
 * 
 */

@Controller("userInterviewersGetViewController")
@RequiredArgsConstructor

public class InterviewersGetViewController {
	
	
	/*
	 * 実施者一覧表示処理
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイルパス
	 * 
	 */
	
	@GetMapping("/user/interviewers_view")
	public String interviewersView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_user_id");
		
		if (loginId == null) {
			
			return "redirect:/user/login";
			
		}
		
		
		// f5更新にも対応
		
		if (!model.containsAttribute("interviewers")) {
			
			return "redirect:/user/interviewers";
			
		}
	
		
		return "user/interviewers_view";
			

		
	}

}
