package com.example.demo.controller.view.manager;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

/*
 * 管理者情報取得結果表示処理群
 * 
 * @author ys
 * 
 */

@Controller("managerGetViewController")
@RequiredArgsConstructor

public class ManagerGetViewController {
	
	
	/*
	 * 管理者情報表示処理
	 * 
	 * @param session セッション値情報
	 * @param model モデル値情報
	 * @return 対象テンプレートファイルパス
	 * 
	 */
	
	@GetMapping("/manager/manager_view")
	public String managerView(HttpSession session, Model model) {
			
		Integer loginId = (Integer)session.getAttribute("log_manager_id");
		
		if (loginId == null) {
			
			return "redirect:/manager/login";
			
		}
		
		
		// f5更新にも対応
		
		if (!model.containsAttribute("manager")) {
			
			return "redirect:/manager/manager_get";
			
		}
		
		
		
		return "manager/manager_view";
		
			
			

		
	}

}
