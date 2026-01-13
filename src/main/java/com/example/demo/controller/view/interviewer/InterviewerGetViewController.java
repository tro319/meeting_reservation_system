package com.example.demo.controller.view.interviewer;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

/*
 * 実施者情報取得結果表示処理群
 * 
 * @author ys
 * 
 */

@Controller("interviewerGetViewController")
@RequiredArgsConstructor

public class InterviewerGetViewController {
	
	
	/*
	 * 実施者情報表示処理
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイルパス
	 * 
	 */
	
	@GetMapping("/interviewer/interviewer_view")
	public String interviewerView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_interviewer_id");
		
		if (loginId == null) {
			
			return "redirect:/interviewer/login";
			
		}
		
		
		// f5更新にも対応
		
		if (!model.containsAttribute("interviewer")) {
			
			return "redirect:/interviewer/interviewer_get";
			
		}
		
		
		
		return "interviewer/interviewer_view";
		
			
			

		
	}

}
