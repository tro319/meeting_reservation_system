package com.example.demo.controller.view.manager;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.form.manager.SearchForm;

import lombok.RequiredArgsConstructor;

/*
 * 実施者一覧取得結果表示処理群
 * 
 * @author ys
 * 
 */

@Controller("managerInterviewersGetViewController")
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
	
	@GetMapping("/manager/interviewers_view")
	public String interviewersView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		
		// f5更新にも対応
		
		if (!model.containsAttribute("interviewers")) {
			
			return "redirect:/manager/interviewers";
			
		}
		
		SearchForm searchForm = new SearchForm();
		
		model.addAttribute("search_form", searchForm);
		
		return "manager/interviewers_view";
			

		
	}

}
