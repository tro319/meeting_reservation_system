package com.example.demo.controller.view.manager;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

/*
 * 実施者情報更新フォーム表示処理
 * 
 * @author ys
 * 
 */

@Controller("managerInterviewerSetViewController")
@RequiredArgsConstructor

public class InterviewerSetViewController {
	
	
	/*
	 * 実施者情報更新フォーム表示処理
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイルパス
	 * 
	 */
	
	@GetMapping("/manager/interviewer_update_view")
	public String formView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		
		// f5更新にも対応
		
		if (!model.containsAttribute("interviewer_set_data")) {
			
			Integer interviewerId = (Integer)session.getAttribute("interviewer_set_id");
			
			if (interviewerId > 0) {
				
				return "redirect:/manager/interviewer_update?id=" + interviewerId;
				
			}
			
			return "redirect:/manager/interviewers";
			
			
		}
		
		
		return "manager/interviewer_update_view";
			

		
	}

}
