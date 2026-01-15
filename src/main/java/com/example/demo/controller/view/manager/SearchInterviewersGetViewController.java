package com.example.demo.controller.view.manager;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.form.manager.SearchForm;

import lombok.RequiredArgsConstructor;

/*
 * 実施者一覧取得結果表示処理群 (検索後)
 * 
 * @author ys
 * 
 */

@Controller("managerSearchInterviewersGetViewController")
@RequiredArgsConstructor

public class SearchInterviewersGetViewController {
	
	
	/*
	 * 実施者一覧表示処理 (検索後)
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイルパス
	 * 
	 */
	
	@GetMapping("/manager/interviewers_view/search")
	public String interviewersView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		} else {
			
			// f5更新にも対応
			
			if (!model.containsAttribute("interviewers")) {
				
				SearchForm searchForm = (SearchForm)session.getAttribute("search_form");
				
				if (searchForm != null) {
					
					return "redirect:/manager/interviewers/search?name=" + searchForm.getName();
					
				}
				
				return "redirect:/manager/interviewers";
				
			}
			
			
			
			return "manager/interviewers_view";
			
		}
		
		
	
			

		
	}

}
